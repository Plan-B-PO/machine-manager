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
public class ComputationStep {
    List<ComputationStepParam> params;
    String artifactUrl;
    String command;
}
