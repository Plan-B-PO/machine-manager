package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.form.MachineForm;

import java.util.List;

public interface MachineService {

    List<Machine> getMachines(Long supplierId);

    Boolean createMachine(MachineForm machine);

    Machine getMachine(Long machineId);

    Boolean deleteMachine(Long machineId);
}
