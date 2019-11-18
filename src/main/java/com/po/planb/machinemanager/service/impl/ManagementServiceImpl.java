package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import com.po.planb.machinemanager.model.Resource;
import com.po.planb.machinemanager.model.Result;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.repository.ManagementRepository;
import com.po.planb.machinemanager.service.ManagementService;
import com.po.planb.machinemanager.utils.NameValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;

    public ManagementServiceImpl(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @Override
    public List<Machine> getMachines(Long supplierId) {
        return managementRepository.findBySupplierId(supplierId);
    }

    @Override
    public Result createMachine(MachineForm machine) {
        String name = machine.getName();
        if (NameValidator.validate(name) && managementRepository.findByName(name) == null) {
            try {
                Machine savedMachine = managementRepository.save(map(machine));
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
        Optional<Machine> machine = managementRepository.findById(machineId);
        return machine.orElse(null);
    }

    @Override
    public HttpStatus deleteMachine(Long machineId) {
        try {
            managementRepository.deleteById(machineId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public List<Machine> getAvailableMachines(Resource resource) {
        //TODO remove this mock and get jsonb values from DB
        return List.of(new Machine(1L, "testuuid", "test", true, 1L, null, null, null, null));
//        List<Machine> activeMachines = managementRepository.getActiveMachines();
//        return activeMachines
//                .stream()
//                .filter(machine -> machine.getCpus().getResourceDifference() > resource.getCpus())
//                .filter(machine -> machine.getGpus().getResourceDifference() > resource.getGpus())
//                .filter(machine -> machine.getMemory().getResourceDifference() > resource.getMemory())
//                .filter(machine -> machine.getLocalStorage().getResourceDifference() > resource.getLocalStorage())
//                .collect(Collectors.toList());
    }

    private Machine map(MachineForm machineForm) {
        final Double current = 0d; //default value is 0
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
