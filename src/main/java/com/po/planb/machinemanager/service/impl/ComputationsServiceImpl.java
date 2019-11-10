package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.MachineWithStatus;
import com.po.planb.machinemanager.repository.Computations.MachineWithStatusRepository;
import com.po.planb.machinemanager.service.ComputationsService;

import java.util.List;
import java.util.stream.Collectors;

public class ComputationsServiceImpl implements ComputationsService {
    private final MachineWithStatusRepository machineWithStatusRepository;

    public ComputationsServiceImpl(MachineWithStatusRepository machineWithStatusRepository) {
        this.machineWithStatusRepository = machineWithStatusRepository;
    }

    @Override
    public void checkOnMachinesStatus(MachineWithStatus machineWithStatus) {
        // TODO detect only changes
        machineWithStatusRepository.deleteAll();
        machineWithStatusRepository.save(machineWithStatus);
    }

    @Override
    public List<String> runningMachines() {
        return null;
    }

    @Override
    public List<String> runningMachinesByUser(Long userId) {
        return machineWithStatusRepository.findAllByMachineAppUserId(userId).stream().map(m ->  m.getMachine().getRunnable().getApplicationId()).collect(Collectors.toList());
    }
}
