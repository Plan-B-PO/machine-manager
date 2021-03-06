package com.po.planb.machinemanager.model;

import com.po.planb.machinemanager.model.Computations.ComputationStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MachineComputingStatus {
    MachineMetricsAggregator machineMetrics;
    Map<String, ComputationStatus> computationStatuses;
}

