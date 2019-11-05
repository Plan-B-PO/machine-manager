package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import com.po.planb.machinemanager.model.Result;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.repository.MachineRepository;
import com.po.planb.machinemanager.service.MachineService;
import com.po.planb.machinemanager.utils.NameValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public List<Machine> getMachines(Long supplierId) {
        return machineRepository.findBySupplierId(supplierId);
    }

    @Override
    public Result createMachine(MachineForm machine) {
        String name = machine.getName();
        if (NameValidator.validate(name) && machineRepository.findByName(name) == null) {
            try {
                Machine savedMachine = machineRepository.save(map(machine));
                return new Result(Boolean.TRUE, savedMachine.getUuid());
            } catch (Exception e) {
                return new Result(Boolean.FALSE, "Could not create machine");
            }
        } else {
            return new Result(Boolean.FALSE, "Wrong machine name");
        }
    }


    @Override
    public Machine getMachine(Long machineId) {
        Optional<Machine> machine = machineRepository.findById(machineId);
        return machine.orElse(null);
    }

    @Override
    public Boolean deleteMachine(Long machineId) {
        try {
            machineRepository.deleteById(machineId);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private Machine map(MachineForm machineForm) {
        final Integer current = 0; //default value is 0
        return Machine.builder()
                .supplierId(machineForm.getId())
                .uuid(UUID.randomUUID().toString())
                .name(machineForm.getName())
                .cpus(new Parameters(current, machineForm.getCpu()))
                .gpus(new Parameters(current, machineForm.getGpu()))
                .memory(new Parameters(current, machineForm.getMemory()))
                .localStorage(new Parameters(current, machineForm.getLocalStorage()))
                .build();
    }

}
