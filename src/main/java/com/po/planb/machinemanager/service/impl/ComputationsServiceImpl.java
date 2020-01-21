package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Computations.*;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.repository.Computations.ComputationsDataInformationRepository;
import com.po.planb.machinemanager.repository.Computations.ComputationsRepository;
import com.po.planb.machinemanager.service.ComputationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ComputationsServiceImpl implements ComputationsService {
    private final ComputationsRepository computationsRepository;
    private final ComputationsDataInformationRepository computationsDataInformationRepository;

    public ComputationsServiceImpl(ComputationsRepository computationsRepository, ComputationsDataInformationRepository computationsDataInformationRepository) {
        this.computationsRepository = computationsRepository;
        this.computationsDataInformationRepository = computationsDataInformationRepository;
    }

    @Override
    public List<String> runningMachines() {
        return null;
    }

    @Override
    public ComputationTask createComputationTask(ComputationTask computationTask) {
        return computationsRepository.save(computationTask);
    }

    @Override
    public void activateComputationTask(String createdTaskId, Machine machine) {

    }

    @Override
    public Machine determineBestMachine(List<Machine> machines) {
        return machines.get(0);
    }

    @Override
    public ComputationStatus checkComputationStatus(String id) {
        ComputationDataInformation info = computationsDataInformationRepository.findByComputationId(id);
        if (info != null && info.getStatus() != null) {
            return info.getStatus();
        } else {
            return null;
        }
    }

    @Override
    public String createComputationData() {
        ComputationDataInformation save = computationsDataInformationRepository.save(
                new ComputationDataInformation(ComputationStatus.WAITING));
        return save.getComputationId();
    }

    @Override
    public void updateComputationData(ComputationDataInformation computationDataInformation) {
        computationsDataInformationRepository.save(computationDataInformation);

    }

    @Override
    public void cancelComputationTask(String id) {
        computationsDataInformationRepository.save(new ComputationDataInformation(id, ComputationStatus.CANCELLED));
    }


//    //TODO to refactor
//    private ComputationTask map(ComputationTask form) {
//        ComputationStep computationStep = new ComputationStep(
//                null, "busybox", "command");
//        Runnable runnable = new Runnable(form.getApplication().getId(), computationStep, form.getVersion());
//        ChosenMachine chosenMachine = new ChosenMachine("machineId", form.getUserId(), runnable, "");
//        return ComputationTask.builder()
//                .id(form.getId())
//                .machine(chosenMachine)
//                .status(ComputationStatus.CREATED)
//                .build();
//
//    }

    private Params mapStepParamToParams(ComputationStepParam computationStepParam) {
        Params params = new Params();
        params.setName(computationStepParam.getName());
        params.setType(computationStepParam.getType());
        return params;
    }


}
