package com.po.planb.machinemanager.model.form;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MachineForm {

    @NotNull
    Long id;

    @NotNull
    @Min(1)
    Integer cpu;

    @NotNull
    @Min(1)
    Integer gpu;

    @NotNull
    @Min(1)
    Integer memory;

    @NotNull
    @Min(1)
    Integer localStorage;

}
