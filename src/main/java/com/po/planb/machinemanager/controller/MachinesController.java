package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.MachineDetails;
import com.po.planb.machinemanager.service.MachinesService;
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

    MachinesController(MachinesService machinesService) {
        this.machinesService = machinesService;
    }

    @PostMapping("/status")
    public void updateComputationTasks(ComputationTask computationTasks) {
        machinesService.updateComputationTaskStatus(computationTasks);
    }

    @PostMapping
    public String registerMachine(@RequestBody MachineDetails machineDetails) {
        return machinesService.registerMachine(machineDetails);
    }

    @PostMapping("/computation")
    public ResponseEntity activateComputationTask(@RequestBody ComputationTask computationTask) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(System.getenv("COMPUTATIONS_ENDPOINT"), computationTask, HttpStatus.class);
        return new ResponseEntity(HttpStatus.OK);
    }
}
