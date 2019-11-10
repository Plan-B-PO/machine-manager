package com.po.planb.machinemanager.repository.Computations;

import com.po.planb.machinemanager.model.Computations.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputationsRepository extends MongoRepository<Machine, Optional> {
    Machine findByRunnableApplicationId(String id);
    List<Machine> findAllById();
}
