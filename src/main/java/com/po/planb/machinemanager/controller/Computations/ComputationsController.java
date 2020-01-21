package com.po.planb.machinemanager.controller.Computations;


import com.po.planb.machinemanager.model.Computations.ComputationData;
import com.po.planb.machinemanager.model.Computations.ComputationStatus;
import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.service.ComputationsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/machine-manager/launcher")
public class ComputationsController {

    private final ComputationsService computationsService;

    @Value("http://3.135.236.42:8080/machine/computation")
    private String COMPUTATIONS_ENDPOINT;

    @Value("https://enigmatic-hollows-51365.herokuapp.com/machine-manager/machines/computation")
    private String MACHINE_COMPUTATION_ENDPOINT;

    @Value("https://enigmatic-hollows-51365.herokuapp.com/resources")
    private String RESOURCE_ENDPOINT;

    public ComputationsController(ComputationsService computationsService) {
        this.computationsService = computationsService;
    }

    @PostMapping("/create")
    public ResponseEntity createComputationTask(ComputationTask computationTask) {
        computationsService.createComputationTask(computationTask);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/computations")
    public String runComputationTask(@RequestBody ComputationTask computationTask) {
        ComputationTask ct = computationsService.createComputationTask(computationTask);
        RestTemplate restTemplate = new RestTemplate();
        List<Machine> machines = List.of(
                Objects.requireNonNull(
                        restTemplate.postForObject(
                                RESOURCE_ENDPOINT,
                                new Resource(1d, 1d, 1d, 1d),
                                Machine[].class), "Available machines cannot be null"
                )
        );
        if (!CollectionUtils.isEmpty(machines)) {
            String computationId = computationsService.createComputationData();
            Machine machine = computationsService.determineBestMachine(machines);
            ComputationData computationData = new ComputationData(ct, machine.getUuid(), computationId);
            restTemplate.postForObject(MACHINE_COMPUTATION_ENDPOINT, computationData, ResponseEntity.class);
            return computationId;
        } else {
            return "No machine is available";
        }
    }

    @DeleteMapping("/computations/{id}")
    public ResponseEntity cancelComputationTask(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();
        computationsService.cancelComputationTask(id);
        restTemplate.delete(COMPUTATIONS_ENDPOINT + "/" + id, id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/computations/{id}")
    public ComputationStatus checkComputationStatus(@PathVariable String id) {
        return computationsService.checkComputationStatus(id);
    }
}
