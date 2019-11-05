package com.po.planb.machinemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull
public class Result {
    Boolean status;
    String value;
}
