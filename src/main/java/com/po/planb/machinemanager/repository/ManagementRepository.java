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
    List<Machine> findBySupplierId(@Param("supplierId") String supplierId);

    @Query("SELECT m FROM Machine m WHERE m.name = :name AND m.supplierId = :username")
    Machine findByName(@Param("name") String name, @Param("username") String username);

    @Query("SELECT m FROM Machine m WHERE m.status = 1")
    List<Machine> getActiveMachines();

    @Query("UPDATE Machine SET status = 2 WHERE uuid = :uuid")
    void update(@Param("uuid") String uuid);

}
