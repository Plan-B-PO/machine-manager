package com.po.planb.machinemanager.service;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import com.po.planb.machinemanager.model.Machine;

import java.util.List;

public interface MachinesService {

    void updateComputationTaskStatus(ComputationTask computationTask);

}
