package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.repository.MachineRepository;
import com.po.planb.machinemanager.service.MachineService;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public List<Machine> getMachines(Long supplierId) {
        return Lists.newArrayList(machineRepository.findBySupplierId(supplierId));
    }

    @Override
    public Boolean createMachine(MachineForm machine) {
        try {
            machineRepository.save(map(machine));
            return Boolean.TRUE;
        }catch(Exception e){
            return Boolean.FALSE;
        }
    }


    @Override
    public Optional<Machine> getMachine(Long machineId) {
        return machineRepository.findById(machineId);
    }

    @Override
    public Boolean deleteMachine(Long machineId) {
        try {
            machineRepository.deleteById(machineId);
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    //TODO need to pass supplierId and id machine that's been created
    private Machine map(MachineForm machineForm) {
        final Integer max = 10;
        return Machine.builder()
//                .id()
                .supplierId(123L)
                .cpus(new Parameters(machineForm.getCpu(), max))
                .gpus(new Parameters(machineForm.getGpu(), max))
                .memory(new Parameters(machineForm.getMemory(), max))
                .localStorage(new Parameters(machineForm.getLocalStorage(), max))
                .build();
    }
}
