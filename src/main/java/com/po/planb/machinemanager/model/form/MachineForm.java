package com.po.planb.machinemanager.model.form;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
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
    String name;

    @NotNull
    @Min(0)
    @Max(100)
    Double cpu;

    @NotNull
    @Min(0)
    @Max(100)
    Double gpu;

    @NotNull
    @Min(0)
    @Max(100)
    Double memory;

    @NotNull
    @Min(0)
    @Max(100)
    Double localStorage;

}
