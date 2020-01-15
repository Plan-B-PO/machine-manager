package com.po.planb.machinemanager.controller;

import com.auth0.SessionUtils;
import com.po.planb.machinemanager.auth0.SupplierService;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Result;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/machine-manager/management")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService, SupplierService supplierService) {
        this.managementService = managementService;
    }

    @RequestMapping("/machines")
    public String getMachines(final Map<String, Object> model, final HttpServletRequest req) {
        String username = (String) SessionUtils.get(req, "username");
        String role = (String) SessionUtils.get(req, "role");
        List<Machine> machines = managementService.getMachines(username, role);
        model.put("machines", machines);
        model.put("supplierId", username);
        return "machines";
    }

    @PostMapping("/machines")
    public String createMachine(@ModelAttribute(name = "machine") @Valid MachineForm machine, final Map<String, Object> model, final HttpServletRequest req) {
        String username = (String) SessionUtils.get(req, "username");
        Result result = managementService.createMachine(machine, username);
        model.put("result", result);
        model.put("supplierId", username);
        return "result";
    }

    @RequestMapping("/machine/{machineId}")
    public String getMachineDetails(final Map<String, Object> model, final HttpServletRequest req,
                                    @PathVariable String machineId) {
        String username = (String) SessionUtils.get(req, "username");
        Machine machine = managementService.getMachine(Long.valueOf(machineId));
        model.put("machine", machine);
        model.put("id", username);
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

