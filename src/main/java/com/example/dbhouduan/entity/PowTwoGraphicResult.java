package com.example.dbhouduan.entity;

import lombok.Data;

@Data
public class PowTwoGraphicResult {
    private Double  chain_growth_rate;
    private Integer fork_number;

    public PowTwoGraphicResult(Double chain_growth_rate, Integer fork_number) {
        this.chain_growth_rate = chain_growth_rate;
        this.fork_number = fork_number;
    }
}
