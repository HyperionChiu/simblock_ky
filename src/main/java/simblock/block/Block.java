/*
 * Copyright 2019 Distributed Systems Group
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package simblock.block;
import simblock.node.Node;

import java.util.*;

import static simblock.settings.SimulationConfiguration.OUROBOROS_SLOT;

/**
 * The representation of a block.
 */
public class Block {
  /**
   * The current height of the block.
   */
  private final int height;
  private final long Ouroboros_slot[]=OUROBOROS_SLOT;
  /**
   * The parent {@link Block}.
   */
  private final Block parent;
  public List<Block> children;
  public long now_weight;

  /**
   * The {@link Node} that minted the block.
   */
  private final Node minter;

  /**
   * Minting timestamp, absolute time since the beginning of the simulation.
   */
  private final long time;

  /**
   * Block unique id.
   */
  private final int id;

  /**
   * Latest known block id.
   */
  private static int latestId = 0;

  /**
   * Instantiates a new Block.
   *
   * @param parent the parent
   * @param minter the minter
   * @param time   the time
   */
  public Block(Block parent, Node minter, long time) throws CloneNotSupportedException {
    this.height = parent == null ? 0 : parent.getHeight() + 1;
    this.parent = parent;
    this.minter = minter;
    this.time = time;
    this.id = latestId;
    latestId++;
    if(this.parent != null) {
      if(this.parent.children == null) {
        this.parent.children = new ArrayList<Block>();
      }
      this.parent.children.add(this);
    }

    this.calculateWeight();
    System.out.println(this.findRoot(this).now_weight+"========================");
  }

  /**
   * Get height int.
   *
   * @return the int
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Get parent block.
   *
   * @return the block
   */
  public Block getParent() {
    return this.parent;
  }

  /**
   * Get minter node.
   *
   * @return the node
   */
  @SuppressWarnings("unused")
  public Node getMinter() {
    return this.minter;
  }

  /**
   * Get time.
   *
   * @return the time
   */
  //TODO what format
  public long getTime() {
    return this.time;
  }

  /**
   * Gets the block id.
   *
   * @return the id
   */
  //TODO what format
  public int getId() {
    return this.id;
  }

  /**
   * Generates the genesis block. The parent is set to null and the time is set to 0
   *
   * @param minter the minter
   * @return the block
   */
  @SuppressWarnings("unused")
  public static Block genesisBlock(Node minter) throws CloneNotSupportedException {
    return new Block(null, minter, 0);
  }

  /**
   * Recursively searches for the block at the provided height.
   *
   * @param height the height
   * @return the block with the provided height
   */
  public Block getBlockWithHeight(int height) {
    if (this.height == height) {
      return this;
    } else {
      return this.parent.getBlockWithHeight(height);
    }
  }
  public Block getParentByOuroboros(){
    Block root = this.getBlockWithHeight(0);

    return root;
  }
  //ghost算法的parents的寻找机制--选取最重且最深的树
  public Block getBlockByGhost(){
    Block root = this.getBlockWithHeight(0);
    Block target_parent = root;
    Block target_child=null;
    while(target_parent.now_weight>1) {
      long weight = 1;
      for (Block child:target_parent.children){
        if(weight==1){
          weight=child.now_weight;
          target_child=child;
        }
        //第一步时,由于now_weight一定小于weight,此时该部分的elif一定会执行,所以无需考虑target_child未更新为当前子树的child区块的情况
        else if(weight<child.now_weight&&weight>1){
            weight=child.now_weight;
            target_child=child;
            //此处应考虑算法的robustness,但是为了省事先不加那个elif
          }else{
              if(target_child.calculateDepth(target_child)<child.calculateDepth(child)){
                target_child=child;
              }
        }
      }
      target_parent=target_child;
    }
    return target_parent;
  }

  /**
   * Checks if the provided block is on the same chain as self.
   *
   * @param block the block to be checked
   * @return true if block are on the same chain false otherwise
   */
  public boolean isOnSameChainAs(Block block) {
    if (block == null) {
      return false;
    } else if (this.height <= block.height) {
      return this.equals(block.getBlockWithHeight(this.height));
    } else {
      return this.getBlockWithHeight(block.height).equals(block);
    }
  }
  //计算树的重
  public long calculateWeight() throws CloneNotSupportedException {
    Block target = this;
        if(this.parent==null){
          this.now_weight=1;
          return 1;
        }
        this.now_weight=1;
        while(target.parent!=null){
          target = target.parent;
          target.now_weight+=1;
        }

    return target.now_weight;}
  //层序遍历子树计算深度,复杂度为O(n)
  public long calculateDepth(Block target_block){
    long depth = 0;
    Queue<Block> dfs_block = new LinkedList<>();
    Stack<Block> dfs_child = new Stack<>();
      dfs_block.offer(target_block);
      while(!dfs_block.isEmpty()){
        while(!dfs_block.isEmpty()){
          Block pb = dfs_block.poll();
          if(pb.children!=null) {
            for (Block b : pb.children) {
//              System.out.println(b + "111111111111111111");
              dfs_child.push(b);
            }
          }
        }
        while(!dfs_child.isEmpty()){
          dfs_block.offer(dfs_child.pop());
        }
        depth++;
      }

    return depth;
  }
  public Block findRoot(Block target_block) throws CloneNotSupportedException {
    if(target_block!=null){
    Block root = target_block;
    while(root.parent!=null) {
      root = root.parent;
    }
    return root;}
    return target_block;
  }
}


