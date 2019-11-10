package com.po.planb.machinemanager.controller.Computations;


import com.po.planb.machinemanager.model.Computations.MachineWithStatus;
import com.po.planb.machinemanager.service.impl.ComputationsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputationsController {
    private final ComputationsServiceImpl service;

    public ComputationsController(ComputationsServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/machine/running")
    private void activeCheckMachineStatus(@RequestBody MachineWithStatus newMachine) {
        service.checkOnMachinesStatus(newMachine);
    }

    @GetMapping("/machine/running")
    private List<String> runningMachines() {
        return service.runningMachines();
    }

    @GetMapping("/machine/running/{userID}")
    private List<String> runningMachines(@PathVariable Long UserId) {
        return service.runningMachinesByUser(UserId);
    }

}
