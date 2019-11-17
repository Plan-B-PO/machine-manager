package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.service.ManagementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {

    private final ManagementService managementService;

    public ResourceController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("/resources")
    public List<Machine> getAvailableMachines(@RequestBody Resource resource) {
        return managementService.getAvailableMachines(resource);
    }
}
