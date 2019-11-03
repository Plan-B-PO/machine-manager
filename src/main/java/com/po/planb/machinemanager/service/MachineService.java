package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.form.MachineForm;

import java.util.List;
import java.util.Optional;

public interface MachineService {

    List<Machine> getMachines(Long supplierId);

    String createMachine(MachineForm machine);

    Optional<Machine> getMachine(Long machineId);

    Boolean deleteMachine(Long machineId);
}
