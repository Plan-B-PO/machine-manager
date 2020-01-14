package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.*;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.repository.ManagementRepository;
import com.po.planb.machinemanager.service.ManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;

    public ManagementServiceImpl(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @Override
    public List<Machine> getMachines(String supplierId) {
        return managementRepository.findBySupplierId(supplierId);
    }

    @Override
    public Result createMachine(MachineForm machine) {
        String name = machine.getName();
        if (managementRepository.findByName(name) == null) {
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
//        return List.of(new Machine(1L, "testuuid", "test", Status.WAITING, "id", null, null, null, null));
        List<Machine> activeMachines = managementRepository.getActiveMachines();
        List<Machine> filteredActiveMachines = activeMachines
                .stream()
                .filter(machine -> machine.getCpus().calculateResourceDifference() > resource.getCpus())
                .filter(machine -> machine.getGpus().calculateResourceDifference() > resource.getGpus())
                .filter(machine -> machine.getMemory().calculateResourceDifference() > resource.getMemory())
                .filter(machine -> machine.getLocalStorage().calculateResourceDifference() > resource.getLocalStorage())
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filteredActiveMachines)) {
            throw new NoSuchElementException("No machines available");
        }
        return filteredActiveMachines;
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
                .status(Status.UNKNOWN)
                .build();
    }

}
