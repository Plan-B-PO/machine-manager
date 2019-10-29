package com.po.planb.machinemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameters implements Serializable {
    Integer current;
    Integer max;
}
