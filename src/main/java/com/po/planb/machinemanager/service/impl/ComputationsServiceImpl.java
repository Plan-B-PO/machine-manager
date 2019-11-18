package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.Runnable;
import com.po.planb.machinemanager.model.Computations.*;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.repository.Computations.ComputationsRepository;
import com.po.planb.machinemanager.repository.Computations.MachineWithStatusRepository;
import com.po.planb.machinemanager.service.ComputationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputationsServiceImpl implements ComputationsService {
    private final MachineWithStatusRepository machineWithStatusRepository;
    private final ComputationsRepository computationsRepository;

    public ComputationsServiceImpl(MachineWithStatusRepository machineWithStatusRepository,
                                   ComputationsRepository computationsRepository) {
        this.machineWithStatusRepository = machineWithStatusRepository;
        this.computationsRepository = computationsRepository;
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
    public List<String> runningMachinesByUser(String userId) {
        return machineWithStatusRepository.findAllByComputationTaskRunnableAppId(userId).stream().map(m -> m.getComputationTask().getRunnable().getAppId()).collect(Collectors.toList());
    }

    @Override
    public ComputationTask createComputationTask(ComputationTaskForm computationTaskForm) {
        return computationsRepository.save(map(computationTaskForm));
    }

    @Override
    public void activateComputationTask(String createdTaskId, Machine machine) {

    }

    @Override
    public Machine determineBestMachine(List<Machine> machines) {
        //TODO select best machine
        return machines.get(0);
    }

    //TODO to refactor
    private ComputationTask map(ComputationTaskForm form) {
        ComputationSteps computationSteps = new ComputationSteps(
                null, form.getUrl(), null);
        Runnable runnable = new Runnable(null, computationSteps, form.getVersion());
        return ComputationTask.builder()
                .userId(form.getUserId())
                .runnable(runnable)
                .status(ComputationStatus.CREATED)
                .build();

    }


}
