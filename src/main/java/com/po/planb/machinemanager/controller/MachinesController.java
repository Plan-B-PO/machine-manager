package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.service.MachinesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
}
