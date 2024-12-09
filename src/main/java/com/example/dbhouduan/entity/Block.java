package com.example.dbhouduan.entity;

import lombok.Data;

@Data
public class Block {
    private String block_name;
    private String parent_name;

    public Block(String block_name, String parent_name) {
        this.block_name = block_name;
        this.parent_name = parent_name;
    }

    public Block() {
    }
}
