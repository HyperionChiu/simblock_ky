package com.example.dbhouduan.entity;

import lombok.Data;

@Data
public class PowProtocolSimulatorInput {
    private Integer node_number;
    private Double block_production_probability_per_unit_time;
    private Integer latency;
    private Integer running_time;
    private String  consensus_algorithm;
}
