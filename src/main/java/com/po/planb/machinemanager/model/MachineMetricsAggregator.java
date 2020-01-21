package com.po.planb.machinemanager.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MachineMetricsAggregator {
    List<MachineMetrics> cpus;
    List<MachineMetrics> gpus;
    List<MachineMetrics> memory;
    List<MachineMetrics> localStorage;
}
