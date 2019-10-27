package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Machine;

import java.util.List;

public interface MachineService {

    List<Machine> getMachines(Long supplierId);

    Machine getMachine(Long machineId);

    Long createMachine(Machine machine);

    Boolean deleteMachine(Long machineId);
}
