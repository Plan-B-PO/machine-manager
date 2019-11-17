package com.po.planb.machinemanager.controller.Computations;


import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Computations.ComputationTaskForm;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.service.impl.ComputationsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/machine-manager/launcher")
public class ComputationsController {
    private final ComputationsServiceImpl computationsService;
    @Value("https://enigmatic-hollows-51365.herokuapp.com/resources")
    private String url;
    @Value("http://34.67.1.173:8080/machine/computation/1")
    private String activateUrl;

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
                        restTemplate.postForObject(url, new Resource(), Machine[].class)
                )
        );
        //TODO select best machine
        computationTask.setToken(machines.get(0).getUuid());
        restTemplate.postForObject(activateUrl, computationTask, HttpStatus.class);
        return new ResponseEntity(HttpStatus.OK);
    }

}
