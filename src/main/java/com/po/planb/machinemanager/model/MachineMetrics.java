package com.po.planb.machinemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineMetrics implements Serializable {
    Double current;
    Double max;

    @JsonIgnore
    public Double calculateResourceDifference() {
        return this.max - this.current;
    }
}
