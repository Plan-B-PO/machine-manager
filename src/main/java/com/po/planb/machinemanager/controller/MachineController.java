package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.service.impl.MachineServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/machine-manager/managment")
public class MachineController {

    private final MachineServiceImpl machineService;

    public MachineController(MachineServiceImpl machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/machines")
    public List<Machine> getMachines(Long supplierId) {
        return Collections.emptyList();
    }

    @PostMapping("/machines")
    public Long createMachine(Machine machine) {
        return null;
    }

    @GetMapping("/machine/{machineId}")
    public Machine getMachine(@PathVariable Long machineId) {
        return new Machine();
    }
}

