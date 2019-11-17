package com.po.planb.machinemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameters implements Serializable {
    Double current;
    Double max;

    public Double getResourceDifference() {
        return this.max - this.current;
    }
}
