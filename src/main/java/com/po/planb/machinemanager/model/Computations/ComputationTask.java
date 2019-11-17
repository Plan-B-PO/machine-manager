package com.po.planb.machinemanager.model.Computations;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComputationTask {
    String computationId;
    String token;
    String userId;
    ComputationStatus status;
    Runnable runnable;
    String logger;
}
