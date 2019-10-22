package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.service.impl.MachineServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/machine-manager/managment")
public class MachineController {

    private MachineServiceImpl machineService;

    @GetMapping("/machines")
    public List<Machine> getMachines(Long supplierId) {
        return Collections.emptyList();
    }
}
