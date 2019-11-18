package com.po.planb.machinemanager.repository;

import com.po.planb.machinemanager.model.Computations.ComputationTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputationsRepository extends MongoRepository<ComputationTask, String> {
}
