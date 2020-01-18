package com.po.planb.machinemanager.repository.Computations;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputationsRepository extends MongoRepository<ComputationTask, Optional> {
    ComputationTask findById(String token);
}
