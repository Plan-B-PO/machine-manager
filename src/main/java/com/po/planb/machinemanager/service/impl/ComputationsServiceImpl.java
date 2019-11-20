package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.Runnable;
import com.po.planb.machinemanager.model.Computations.*;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.repository.Computations.ComputationsRepository;
import com.po.planb.machinemanager.service.ComputationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputationsServiceImpl implements ComputationsService {
    private final ComputationsRepository computationsRepository;

    public ComputationsServiceImpl(ComputationsRepository computationsRepository) {
        this.computationsRepository = computationsRepository;
    }

    @Override
    public List<String> runningMachines() {
        return null;
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
                mapStepParamToParams(form.getApplication().getSchema()), "busybox", "command");
        Runnable runnable = new Runnable(form.getApplication().getId(), computationSteps, form.getVersion());
        ChosenMachine chosenMachine = new ChosenMachine("machineId", form.getUserId(), runnable, "");
        return ComputationTask.builder()
                .machine(chosenMachine)
                .status(ComputationStatus.CREATED)
                .build();

    }

    private Params mapStepParamToParams(ComputationStepParam computationStepParam) {
        Params params = new Params();
        params.setName(computationStepParam.getName());
        params.setType(computationStepParam.getType());
        return params;
    }


}
