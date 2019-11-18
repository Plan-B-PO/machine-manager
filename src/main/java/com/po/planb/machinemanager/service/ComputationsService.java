package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Computations.ComputationTaskForm;
import com.po.planb.machinemanager.model.Computations.MachineWithStatus;
import com.po.planb.machinemanager.model.Machine;

import java.util.List;

public interface ComputationsService {
    void checkOnMachinesStatus(MachineWithStatus machineWithStatus);

    List<String> runningMachines();

    List<String> runningMachinesByUser(String userId);

    ComputationTask createComputationTask(ComputationTaskForm computationTaskForm);

    void activateComputationTask(String createdTaskId, Machine machine);

    Machine determineBestMachine(List<Machine> machines);
}
