package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Computations.ComputationData;
import com.po.planb.machinemanager.model.Computations.ComputationDataInformation;
import com.po.planb.machinemanager.model.MachineDetails;
import com.po.planb.machinemanager.service.ComputationsService;
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

    private final MachinesService machinesService;
    private final ComputationsService computationsService;

    @Value("http://3.135.236.42:8080/machine/computation")
    private String COMPUTATIONS_ENDPOINT;

    MachinesController(MachinesService machinesService, ComputationsService computationsService) {
        this.machinesService = machinesService;
        this.computationsService = computationsService;
    }

    @PostMapping("/status")
    public void updateComputationTasks(@RequestBody ComputationDataInformation computationDataInformation) {
        computationsService.updateComputationData(computationDataInformation);
    }

    @PostMapping
    public String registerMachine(@RequestBody MachineDetails machineDetails) {
        return machinesService.registerMachine(machineDetails);
    }

    @PostMapping("/computation")
    public ResponseEntity activateComputationTask(@RequestBody ComputationData computationData) {
        RestTemplate restTemplate = new RestTemplate();
        machinesService.activateMachine(computationData.getToken());
        //TODO uncomment when Machine is ready for our request
//        restTemplate.postForObject(COMPUTATIONS_ENDPOINT, computationData, Void.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Other endopint is used for this task @PostMapping("/status")
//    @PostMapping("/status/{id}")
//    public ResponseEntity reportMetrics(MachineComputingStatus machineComputingStatus){
//        //TODO retrieve status and update machine status
//        return new ResponseEntity((HttpStatus.OK));
//    }

//    @PostMapping("/initial-connect")
//    public void connectMachine(@RequestBody MachineComputingStatus machineComputingStatus) {
//        String token = ?
//        machinesService.connectMachine(token);
//    }
}
