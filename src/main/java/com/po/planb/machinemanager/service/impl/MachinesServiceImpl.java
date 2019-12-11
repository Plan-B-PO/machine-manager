package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.MachineDetails;
import com.po.planb.machinemanager.repository.Computations.ComputationsRepository;
import com.po.planb.machinemanager.repository.MachineDetailsRepository;
import com.po.planb.machinemanager.repository.ManagementRepository;
import com.po.planb.machinemanager.service.MachinesService;
import org.springframework.stereotype.Service;

@Service
public class MachinesServiceImpl implements MachinesService {

    private final ComputationsRepository computationsRepository;
    private final MachineDetailsRepository machineDetailsRepository;
    private final ManagementRepository managementRepository;

    public MachinesServiceImpl(ComputationsRepository computationsRepository, MachineDetailsRepository machineDetailsRepository, ManagementRepository managementRepository) {
        this.computationsRepository = computationsRepository;
        this.machineDetailsRepository = machineDetailsRepository;
        this.managementRepository = managementRepository;
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

    @Override
    public void activateMachine(String token) {
        managementRepository.update(token);
    }
}
