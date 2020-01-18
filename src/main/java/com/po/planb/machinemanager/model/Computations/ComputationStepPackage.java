package com.po.planb.machinemanager.model.Computations;

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
public class ComputationStepPackage {
    String applicationId;
    List<ComputationStep> computationSteps;
    String version;
}
