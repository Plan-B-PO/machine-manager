package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.repository.MachineRepository;
import com.po.planb.machinemanager.service.MachineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public List<Machine> getMachines(Long supplierId) {
        return machineRepository.getMachines(supplierId);
    }

    @Override
    public Boolean createMachine(MachineForm machine) {
        return machineRepository.createMachine(map(machine));
    }

    @Override
    public Machine getMachine(Long machineId) {
        return null;
    }

    @Override
    public Boolean deleteMachine(Long machineId) {
        return null;
    }

    //TODO need to pass supplierId and id machine that's been created
    private Machine map(MachineForm machineForm) {
        final Integer max = 10;
        return Machine.builder()
//                .id()
//                .supplierId()
                .cpus(new Parameters(machineForm.getCpu(), max))
                .gpus(new Parameters(machineForm.getGpu(), max))
                .memory(new Parameters(machineForm.getMemory(), max))
                .localStorage(new Parameters(machineForm.getLocalStorage(), max))
                .build();
    }
}
