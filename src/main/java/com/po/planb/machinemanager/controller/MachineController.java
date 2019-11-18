package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/machine")
public class MachineController {


    @PostMapping("/computation")
    public ResponseEntity activateComputationTask(@RequestBody ComputationTask computationTask) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(System.getenv("COMPUTATIONS_ENDPOINT"), computationTask, HttpStatus.class);
        return new ResponseEntity(HttpStatus.OK);
    }
}
