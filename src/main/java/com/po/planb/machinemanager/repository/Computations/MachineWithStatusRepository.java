package com.po.planb.machinemanager.repository.Computations;

import com.po.planb.machinemanager.model.Computations.MachineWithStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MachineWithStatusRepository extends MongoRepository<MachineWithStatus, String> {
    List<MachineWithStatus> findAllByMachineAppUserId(Long id);
}
