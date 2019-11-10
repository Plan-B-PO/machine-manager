package com.po.planb.machinemanager.model.Computations;


public class MachineWithStatus {
    private Machine machine;
    private ContainerStatus status;

    public MachineWithStatus(Machine machine, ContainerStatus status) {
        this.machine = machine;
        this.status = status;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public ContainerStatus getStatus() {
        return status;
    }

    public void setStatus(ContainerStatus status) {
        this.status = status;
    }
}
