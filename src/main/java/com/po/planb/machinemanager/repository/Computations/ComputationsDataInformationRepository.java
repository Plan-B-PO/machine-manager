package com.po.planb.machinemanager.repository.Computations;

import com.po.planb.machinemanager.model.Computations.ComputationDataInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputationsDataInformationRepository extends MongoRepository<ComputationDataInformation, Optional> {
    ComputationDataInformation findByComputationId(String computationId);
}
