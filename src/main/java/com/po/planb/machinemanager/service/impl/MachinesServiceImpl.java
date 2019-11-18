package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.MachineDetails;
import com.po.planb.machinemanager.repository.ComputationsRepository;
import com.po.planb.machinemanager.repository.MachineDetailsRepository;
import com.po.planb.machinemanager.service.MachinesService;
import org.springframework.stereotype.Service;

@Service
public class MachinesServiceImpl implements MachinesService {

    private final ComputationsRepository computationsRepository;
    private final MachineDetailsRepository machineDetailsRepository;

    public MachinesServiceImpl(ComputationsRepository computationsRepository, MachineDetailsRepository machineDetailsRepository) {
        this.computationsRepository = computationsRepository;
        this.machineDetailsRepository = machineDetailsRepository;
    }

    @Override
    public void updateComputationTaskStatus(ComputationTask computationTask) {
        computationsRepository.save(computationTask);
    }

    @Override
    public String registerMachine(MachineDetails machineDetails) {
        MachineDetails details = machineDetailsRepository.save(machineDetails);
        return details.getId().toString();
    }
}
