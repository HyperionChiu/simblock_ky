package com.example.dbhouduan.entity;

import lombok.Data;

@Data
public class PowSecurityEvaluationInput {
            private Integer probability_tolerance_value;
            private Integer chain_growth_interval;
            private Integer synchronization_tolerance_value;
            private Integer node_number;
            private Double  block_production_probability_per_unit_time;
            private Integer delay_time;
            private Double  delay_probability;
            private String  consensus_algorithm;
}
