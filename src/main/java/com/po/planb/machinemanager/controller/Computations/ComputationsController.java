package com.po.planb.machinemanager.controller.Computations;


import com.po.planb.machinemanager.model.Computations.*;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.service.ComputationsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public @ResponseBody
    ResponseEntity runComputationTask(@RequestBody ComputationTask computationTask) {
        ComputationTask ct = computationsService.createComputationTask(computationTask);
        RestTemplate restTemplate = new RestTemplate();
        List<Machine> machines = List.of(restTemplate.postForObject(
                RESOURCE_ENDPOINT,
                new Resource(1d, 1d, 1d, 1d),
                Machine[].class)
        );
        if (!CollectionUtils.isEmpty(machines)) {
            String computationId = computationsService.createComputationData();
            Machine machine = computationsService.determineBestMachine(machines);
            ComputationData computationData = new ComputationData(ct, machine.getUuid(), computationId);
            restTemplate.postForObject(MACHINE_COMPUTATION_ENDPOINT, computationData, ResponseEntity.class);
            return new ResponseEntity<>(new ComputationTaskId(computationId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No machine Available", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/computations/{id}")
    public ResponseEntity cancelComputationTask(@PathVariable String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = computationsService.cancelComputationTask(id);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            HttpEntity<String> request = new HttpEntity<>(id);
            ResponseEntity<Void> response = restTemplate.exchange(
                    COMPUTATIONS_ENDPOINT + "/" + id, HttpMethod.DELETE, request, Void.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return responseEntity;
            } else
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/computations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity checkComputationStatus(@PathVariable String id) {
        ComputationStatus computationStatus = computationsService.checkComputationStatus(id);
        if (computationStatus != null) {
            return new ResponseEntity(new CheckStatusResponse(computationStatus), HttpStatus.OK);
        } else {
            return new ResponseEntity("No computation task with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
