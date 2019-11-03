package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.service.impl.MachineServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/machine-manager/managment")
public class MachineController {

    private final MachineServiceImpl machineService;

    public MachineController(MachineServiceImpl machineService) {
        this.machineService = machineService;
    }

    @RequestMapping("/machines")
    public String getMachines(Model model, @RequestParam(name = "supplierId") @Valid String supplierId) {
        List<Machine> machines = machineService.getMachines(Long.valueOf(supplierId));
        model.addAttribute("machines", machines);
        model.addAttribute("supplierId", supplierId);
        return "machines";
    }

    @PostMapping("/machines")
    public String createMachine(@ModelAttribute(name = "machine") @Valid MachineForm machine, Model model) {
        String result = machineService.createMachine(machine);
        model.addAttribute("result", result);
        model.addAttribute("supplierId", machine.getId());
        return "result";
    }
}

