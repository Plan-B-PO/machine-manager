package com.po.planb.machinemanager.controller.Computations;


import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Computations.ComputationTaskForm;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.service.ComputationsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/machine-manager/launcher")
public class ComputationsController {

    private final ComputationsService computationsService;

    @Value("http://34.69.134.162:8080/machine/computation")
    private String COMPUTATIONS_ENDPOINT;

    @Value("https://enigmatic-hollows-51365.herokuapp.com/machine-manager/machines/computation")
    private String MACHINE_COMPUTATION_ENDPOINT;

    @Value("https://enigmatic-hollows-51365.herokuapp.com/resources")
    private String RESOURCE_ENDPOINT;

    public ComputationsController(ComputationsService computationsService) {
        this.computationsService = computationsService;
    }

    @PostMapping("/create")
    public ResponseEntity createComputationTask(ComputationTaskForm computationTaskForm) {
        computationsService.createComputationTask(computationTaskForm);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/computations")
    public ResponseEntity runComputationTask(@RequestBody ComputationTaskForm computationTaskForm) {
        ComputationTask computationTask = computationsService.createComputationTask(computationTaskForm);
        RestTemplate restTemplate = new RestTemplate();
        List<Machine> machines = List.of(
                Objects.requireNonNull(
                        restTemplate.postForObject(
                                RESOURCE_ENDPOINT,
                                new Resource(1d, 1d, 1d, 1d),
                                Machine[].class), "Available machines cannot be null"
                )
        );
        computationTask.setToken(computationsService.determineBestMachine(machines).getUuid());
        restTemplate.postForObject(MACHINE_COMPUTATION_ENDPOINT, computationTask, ResponseEntity.class);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/computations/{id}")
    public ResponseEntity cancelComputationTask(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(COMPUTATIONS_ENDPOINT + "/" + id, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/check/computation/{id}")
    public String checkComputationStatus(@PathVariable String id) {
        return computationsService.checkComputationStatus(id);
    }

}
