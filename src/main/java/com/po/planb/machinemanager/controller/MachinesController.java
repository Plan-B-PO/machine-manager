package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.MachineComputingStatus;
import com.po.planb.machinemanager.model.MachineDetails;
import com.po.planb.machinemanager.service.MachinesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/machine-manager/machines")
public class MachinesController {

    private MachinesService machinesService;

    @Value("http://34.69.134.162:8080/machine/computation")
    private String COMPUTATIONS_ENDPOINT;

    MachinesController(MachinesService machinesService) {
        this.machinesService = machinesService;
    }

    @PostMapping("/status")
    public void updateComputationTasks(@RequestBody ComputationTask computationTasks) {
        machinesService.updateComputationTaskStatus(computationTasks);
    }

    @PostMapping
    public String registerMachine(@RequestBody MachineDetails machineDetails) {
        return machinesService.registerMachine(machineDetails);
    }

    @PostMapping("/computation")
    public ResponseEntity activateComputationTask(@RequestBody ComputationTask computationTask) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(COMPUTATIONS_ENDPOINT, computationTask, Void.class);
        machinesService.activateMachine(computationTask.getToken());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/status/{id}")
    public ResponseEntity reportMetrics(MachineComputingStatus machineComputingStatus){
        return new ResponseEntity((HttpStatus.OK));
    }

//    @PostMapping("/initial-connect")
//    public void connectMachine(@RequestBody MachineComputingStatus machineComputingStatus) {
//        String token = ?
//        machinesService.connectMachine(token);
//    }
}
