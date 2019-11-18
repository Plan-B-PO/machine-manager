package com.po.planb.machinemanager.controller.Computations;


import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Computations.ComputationTaskForm;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.service.impl.ComputationsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/machine-manager/launcher")
public class ComputationsController {
    private final ComputationsServiceImpl computationsService;

    public ComputationsController(ComputationsServiceImpl computationsService) {
        this.computationsService = computationsService;
    }

    @PostMapping("/create")
    public ResponseEntity createComputationTask(ComputationTaskForm computationTaskForm) {
        computationsService.createComputationTask(computationTaskForm);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/computations")
    public ResponseEntity runComputationTask(ComputationTaskForm computationTaskForm) {
        ComputationTask computationTask = computationsService.createComputationTask(computationTaskForm);
        RestTemplate restTemplate = new RestTemplate();
        List<Machine> machines = List.of(
                Objects.requireNonNull(
                        restTemplate.postForObject(
                                System.getenv("RESOURCE_ENDPOINT"),
                                new Resource(1d, 1d, 1d, 1d),
                                Machine[].class)
                )
        );
        computationTask.setToken(computationsService.determineBestMachine(machines).getUuid());
        restTemplate.postForObject(System.getenv("MACHINE_COMPUTATION_ENDPOINT"), computationTask, HttpStatus.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/computations/{id}")
    public ResponseEntity cancelComputationTask(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(System.getenv("COMPUTATIONS_ENDPOINT") + "/" + id, id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
