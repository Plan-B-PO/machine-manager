package com.po.planb.machinemanager.repository.Computations;

import com.po.planb.machinemanager.model.Computations.MachineWithStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineWithStatusRepository extends MongoRepository<MachineWithStatus, String> {
    List<MachineWithStatus> findAllByComputationTaskRunnableAppId(String id);
}
