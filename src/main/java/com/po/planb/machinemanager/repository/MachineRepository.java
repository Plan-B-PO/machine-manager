package com.po.planb.machinemanager.repository;

import com.po.planb.machinemanager.model.Machine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MachineRepository extends CrudRepository<Machine, Long> {

    @Query("SELECT m FROM Machine m WHERE m.supplierId = :supplierId")
    List<Machine> findBySupplierId(@Param("supplierId") Long supplierId);

}
