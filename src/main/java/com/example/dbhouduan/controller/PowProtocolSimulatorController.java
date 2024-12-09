package com.example.dbhouduan.controller;

import com.example.dbhouduan.common.Result;
import com.example.dbhouduan.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pow")
public class PowProtocolSimulatorController {



    @PostMapping("/protocolSimulator")
    public Result ProtocolSimulator(@RequestBody PowProtocolSimulatorInput powProtocolSimulatorInput){
        System.out.println(powProtocolSimulatorInput);
        PowProtocolSimulator protocolSimulator=new PowProtocolSimulator(100,100,100,100,100);
        return Result.success(protocolSimulator);
    }

    @PostMapping("/protocolSimulator/showBlockChain")
    public Result ShowBlockChain(@RequestBody PowProtocolSimulatorInput powProtocolSimulatorInput){
        System.out.println(powProtocolSimulatorInput);

        Block block1 =new Block("SimBlock.block.ProofOfWorkBlock@26f7995f","SimBlock.block.ProofOfWorkBlock@51d38c91");
        Block block2 =new Block("SimBlock.block.ProofOfWorkBlock@26f7995g","SimBlock.block.ProofOfWorkBlock@51d38c91");
        Block block3 =new Block("SimBlock.block.ProofOfWorkBlock@26f7995h","SimBlock.block.ProofOfWorkBlock@26f7995g");
        List<Block> list=new ArrayList<>();
        list.add(block1);
        list.add(block2);
        list.add(block3);

        return Result.success(list);
    }

    @PostMapping("/securityEvaluation/securityProperty")
    public Result SecurityProperty(@RequestBody PowSecurityEvaluationInput powSecurityEvaluationInput){
        System.out.println(powSecurityEvaluationInput);

        PowTwoGraphicResult powTwoGraphicResult = new PowTwoGraphicResult(2.5,2);

        return Result.success(powTwoGraphicResult);
    }
}
