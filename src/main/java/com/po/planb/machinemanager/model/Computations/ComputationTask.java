package com.po.planb.machinemanager.model.Computations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComputationTask {
    String id;
    String name;
    String userId;
    ComputationApplication application;
    ComputationInput input;
    ComputationStepPackage computationStepPackage;
}
