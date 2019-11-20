package com.po.planb.machinemanager.model.Computations;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChosenMachine {
    String id;
    Long appUserId;
    Runnable runnable;
    String input;
}
