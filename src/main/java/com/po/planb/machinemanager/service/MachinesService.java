package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.MachineDetails;

public interface MachinesService {

    void updateComputationTaskStatus(ComputationTask computationTask);

    String registerMachine(MachineDetails machineDetails);

    void activateMachine(String token);

//    String connectMachine(String token);
}
