package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Computations.ComputationDataInformation;
import com.po.planb.machinemanager.model.Computations.ComputationStatus;
import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Machine;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ComputationsService {
    List<String> runningMachines();

    ComputationTask createComputationTask(ComputationTask computationTask);

    void activateComputationTask(String createdTaskId, Machine machine);

    Machine determineBestMachine(List<Machine> machines);

    ComputationStatus checkComputationStatus(String id);

    String createComputationData();

    void updateComputationData(ComputationDataInformation computationDataInformation);

    ResponseEntity cancelComputationTask(String id);
}
