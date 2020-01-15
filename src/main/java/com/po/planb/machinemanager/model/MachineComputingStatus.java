package com.po.planb.machinemanager.model;

import com.po.planb.machinemanager.model.Computations.ComputationStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MachineComputingStatus {
    MachineMetrics machineMetrics;
    ComputationStatus computationStatus;
}

