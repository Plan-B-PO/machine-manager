package com.po.planb.machinemanager.model.form;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MachineForm {
    Integer cpu;
    Integer gpu;
    Integer memory;
    Integer localStorage;
}
