package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Computations.MachineWithStatus;

import java.util.List;

public interface ComputationsService {
    void checkOnMachinesStatus(MachineWithStatus machineWithStatus);
    List<String> runningMachines();
    List<String> runningMachinesByUser(Long userId);
}
