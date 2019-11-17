package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.repository.MachinesRepository;
import com.po.planb.machinemanager.service.MachinesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachinesServiceImpl implements MachinesService {

    private final MachinesRepository machinesRepository;

    public MachinesServiceImpl(MachinesRepository machinesRepository) {
        this.machinesRepository = machinesRepository;
    }

    @Override
    public void updateComputationTaskStatus(ComputationTask computationTask) {
        machinesRepository.save(computationTask);
    }
}
