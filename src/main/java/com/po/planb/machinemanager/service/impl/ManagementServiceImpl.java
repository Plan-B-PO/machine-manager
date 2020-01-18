package com.po.planb.machinemanager.service.impl;

import com.po.planb.machinemanager.model.*;
import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.model.role.RoleType;
import com.po.planb.machinemanager.repository.ManagementRepository;
import com.po.planb.machinemanager.service.ManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;

    public ManagementServiceImpl(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    @Override
    public List<Machine> getMachines(String supplierId, String role) {
        if (RoleType.ADMINISTRATOR.name().equalsIgnoreCase(role)) {
            return (List<Machine>) managementRepository.findAll();
        } else {
            return managementRepository.findBySupplierId(supplierId);
        }
    }

    @Override
    public Result createMachine(MachineForm machine, String username) {
        String name = machine.getName();
        if (managementRepository.findByName(name, username) == null) {
            try {
                Machine savedMachine = managementRepository.save(map(machine, username));
                return new Result(Boolean.TRUE, savedMachine.getUuid());
            } catch (Exception e) {
                return new Result(Boolean.FALSE, "Could not create machine");
            }
        } else {
            return new Result(Boolean.FALSE, "Machine with that name already exists");
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

    private Machine map(MachineForm machineForm, String username) {
        final Double current = 0d; //default value is 0
        return Machine.builder()
                .supplierId(username)
                .uuid(UUID.randomUUID().toString())
                .name(machineForm.getName())
                .cpus(new MachineMetrics(current, machineForm.getCpu()))
                .gpus(new MachineMetrics(current, machineForm.getGpu()))
                .memory(new MachineMetrics(current, machineForm.getMemory()))
                .localStorage(new MachineMetrics(current, machineForm.getLocalStorage()))
                .status(Status.UNKNOWN)
                .build();
    }

}
