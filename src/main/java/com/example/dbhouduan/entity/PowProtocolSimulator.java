package com.example.dbhouduan.entity;

import lombok.Data;

@Data
public class PowProtocolSimulator {
         private Integer longest_chain_lengthh;
         private Integer shortest_chain_lengthh;
         private Integer chain_growth_rateh;
         private Integer public_chain_lengthh;
         private Integer fork_length;


    public PowProtocolSimulator() {
    }

    public PowProtocolSimulator(Integer longest_chain_lengthh, Integer shortest_chain_lengthh, Integer chain_growth_rateh, Integer public_chain_lengthh, Integer fork_length) {
        this.longest_chain_lengthh = longest_chain_lengthh;
        this.shortest_chain_lengthh = shortest_chain_lengthh;
        this.chain_growth_rateh = chain_growth_rateh;
        this.public_chain_lengthh = public_chain_lengthh;
        this.fork_length = fork_length;
    }
}
