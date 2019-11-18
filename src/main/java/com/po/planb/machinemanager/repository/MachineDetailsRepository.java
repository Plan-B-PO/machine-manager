package com.po.planb.machinemanager.repository;

import com.po.planb.machinemanager.model.MachineDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineDetailsRepository extends MongoRepository<MachineDetails, String> {
}
