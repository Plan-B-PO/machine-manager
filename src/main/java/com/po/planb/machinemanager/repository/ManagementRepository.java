package com.po.planb.machinemanager.repository;

import com.po.planb.machinemanager.model.Machine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManagementRepository extends CrudRepository<Machine, Long> {

    @Query("SELECT m FROM Machine m WHERE m.supplierId = :supplierId")
    List<Machine> findBySupplierId(@Param("supplierId") Long supplierId);

    @Query("SELECT m FROM Machine m WHERE m.name = :name")
    Machine findByName(@Param("name") String name);

    @Query("SELECT m FROM Machine m WHERE m.status = true")
    List<Machine> getActiveMachines();

}
