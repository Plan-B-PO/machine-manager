package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.service.impl.MachineServiceImpl;
import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/machine-manager/managment")
public class MachineController {

    private final MachineServiceImpl machineService;

    public MachineController(MachineServiceImpl machineService) {
        this.machineService = machineService;
    }

    //TODO change to real supplierId
    @GetMapping("/machines")
    public String getMachines(Model model, Long supplierId) {
        List<Machine> machines = machineService.getMachines(123L);
        model.addAttribute("machines", machines);
        return "machines";
    }

    @PostMapping("/machines")
    public String createMachine(@ModelAttribute(name = "machine") @Valid MachineForm machine, Model model) {
        Boolean result = machineService.createMachine(machine) != null ;
        model.addAttribute("result", result);
        return "result";
    }
}

