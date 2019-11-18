package com.po.planb.machinemanager.controller;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Result;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/machine-manager/management")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @RequestMapping("/machines")
    public String getMachines(Model model, @RequestParam(name = "supplierId") @Valid String supplierId) {
        List<Machine> machines = managementService.getMachines(Long.valueOf(supplierId));
        model.addAttribute("machines", machines);
        model.addAttribute("supplierId", supplierId);
        return "machines";
    }

    @PostMapping("/machines")
    public String createMachine(@ModelAttribute(name = "machine") @Valid MachineForm machine, Model model) {
        Result result = managementService.createMachine(machine);
        model.addAttribute("result", result);
        model.addAttribute("supplierId", machine.getId());
        return "result";
    }

    @RequestMapping("/machine/{machineId}")
    public String getMachineDetails(Model model, @RequestParam(name = "id") @Valid String id,
                                    @PathVariable String machineId) {
        Machine machine = managementService.getMachine(Long.valueOf(machineId));
        model.addAttribute("machine", machine);
        model.addAttribute("id", id);
        return "machineDetails";
    }

    @GetMapping("/machines/{id}")
    public Machine getMachine(@PathVariable String id) {
        return managementService.getMachine(Long.valueOf(id));
    }

    @DeleteMapping("/machines/{id}")
    public ResponseEntity deleteMachine(@PathVariable String id) {
        return new ResponseEntity(managementService.deleteMachine(Long.valueOf(id)));
    }

}

