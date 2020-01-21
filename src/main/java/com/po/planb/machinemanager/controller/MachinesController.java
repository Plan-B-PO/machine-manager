package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Computations.ComputationData;
import com.po.planb.machinemanager.model.Computations.ComputationDataInformation;
import com.po.planb.machinemanager.model.MachineComputingStatus;
import com.po.planb.machinemanager.model.MachineDetails;
import com.po.planb.machinemanager.service.ComputationsService;
import com.po.planb.machinemanager.service.MachinesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
        HttpEntity<ComputationData> request = new HttpEntity<>(computationData);
        ResponseEntity<Void> response = restTemplate
                .exchange(COMPUTATIONS_ENDPOINT, HttpMethod.POST, request, Void.class);
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(
                    "Machine component failed processing request from Machines", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Other endopint is used for this task @PostMapping("/status")
//    @PostMapping("/status/{id}")
//    public ResponseEntity reportMetrics(MachineComputingStatus machineComputingStatus){
//        //TODO retrieve status and update machine status
//        return new ResponseEntity((HttpStatus.OK));
//    }

    @PostMapping("/initial-connect")
    public void connectMachine(@RequestBody MachineComputingStatus machineComputingStatus) {
//        String id = machinesService.connectMachine(machineComputingStatus, "8abc5d96-f3d2-450b-8514-014d6b10e700");
//        return id;
    }
}
