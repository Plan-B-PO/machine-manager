package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.model.Result;
import com.po.planb.machinemanager.model.form.MachineForm;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ManagementService {

    List<Machine> getMachines(String supplierId, String role);

    Result createMachine(MachineForm machine, String username);

    Machine getMachine(Long machineId);

    HttpStatus deleteMachine(Long machineId);

    List<Machine> getAvailableMachines(Resource resource);
}
