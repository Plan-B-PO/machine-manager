package com.po.planb.machinemanager.repository;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MachineRepository {

    //TODO make functionality to get machines from db and insert machine into db
    public List<Machine> getMachines(Long supplierId) {
        Parameters parameters = new Parameters(10, 20);
        Parameters parameters2 = new Parameters(30, 40);
        Parameters parameters3 = new Parameters(50, 60);
        Parameters parameters4 = new Parameters(70, 80);
        Machine machine = new Machine(11232131L, supplierId, parameters, parameters2, parameters3, parameters4);
        Machine machine2 = new Machine(221431243L, supplierId, parameters4, parameters, parameters2, parameters3);
        Machine machine3 = new Machine(3145151L, supplierId, parameters3, parameters4, parameters, parameters2);
        return Arrays.asList(machine, machine2, machine3);
    }

    public Boolean createMachine(Machine machine) {
        return Boolean.TRUE;
    }

    public Machine getMachine(Long machineId) {
        return null;
    }

    public Boolean deleteMachine(Long machineId) {
        return null;
    }

}
