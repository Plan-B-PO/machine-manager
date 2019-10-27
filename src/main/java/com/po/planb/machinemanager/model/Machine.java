package com.po.planb.machinemanager.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
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
