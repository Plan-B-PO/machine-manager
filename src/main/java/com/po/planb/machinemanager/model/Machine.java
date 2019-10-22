package com.po.planb.machinemanager.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Machine {
    Long id;
    Long supplierId;
    Parameters cpus;
    Parameters gpus;
    Parameters memory;
    Parameters localStorage;
}
