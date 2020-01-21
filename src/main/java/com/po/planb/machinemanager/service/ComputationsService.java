package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Computations.ComputationDataInformation;
import com.po.planb.machinemanager.model.Computations.ComputationStatus;
import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Machine;

import java.util.List;

public interface ComputationsService {
    List<String> runningMachines();

    ComputationTask createComputationTask(ComputationTask computationTask);

    void activateComputationTask(String createdTaskId, Machine machine);

    Machine determineBestMachine(List<Machine> machines);

    String checkComputationStatus(String id);

    String createComputationData();

    void updateComputationData(ComputationDataInformation computationDataInformation);

    void cancelComputationTask(String id);
}
