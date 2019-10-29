package com.po.planb.machinemanager.repository;

import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;


@Repository
public class MachineRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //TODO make functionality to get machines from db and insert machine into db
    public List<Machine> getMachines(Long supplierId) {

        List<Machine> machines = jdbcTemplate.query(
                "SELECT * FROM machines WHERE supplierId = ?", new Object[]{supplierId},
                (rs, rowNum) -> new Machine(rs.getLong("id"), rs.getLong("supplierId"),
                        new Parameters(rs.getInt("cpu_current"), rs.getInt("cpu_max")),
                        new Parameters(rs.getInt("gpu_current"), rs.getInt("gpu_max")),
                        new Parameters(rs.getInt("memory_current"), rs.getInt("memory_max")),
                        new Parameters(rs.getInt("local_storage_current"), rs.getInt("local_storage_max")
                        )));
        return machines;
    }

    //TODO change hardcoded supplierId
    public Boolean createMachine(Machine machine) {
        Object[] params = new Object[9];
//        params[0] = machine.getSupplierId();
        params[0] = 123;
        params[1] = machine.getCpus().getCurrent();
        params[2] = machine.getCpus().getMax();
        params[3] = machine.getGpus().getCurrent();
        params[4] = machine.getGpus().getMax();
        params[5] = machine.getMemory().getCurrent();
        params[6] = machine.getMemory().getMax();
        params[7] = machine.getLocalStorage().getCurrent();
        params[8] = machine.getLocalStorage().getMax();
        try {
            jdbcTemplate.batchUpdate("INSERT INTO machines(supplierId, cpu_current, cpu_max, gpu_current, gpu_max, memory_current, memory_max, local_storage_current, local_storage_max) VALUES (?,?,?,?,?,?,?,?,?)",
                    Collections.singletonList(params));
            return Boolean.TRUE;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Machine getMachine(Long machineId) {
        List<Machine> machines = jdbcTemplate.query(
                "SELECT * FROM machines WHERE id = ?", new Object[]{machineId},
                (rs, rowNum) -> new Machine(rs.getLong("id"), rs.getLong("supplierId"),
                        new Parameters(rs.getInt("cpu_current"), rs.getInt("cpu_max")),
                        new Parameters(rs.getInt("gpu_current"), rs.getInt("gpu_max")),
                        new Parameters(rs.getInt("memory_current"), rs.getInt("memory_max")),
                        new Parameters(rs.getInt("local_storage_current"), rs.getInt("local_storage_max")
                        )));
        return machines.get(0);
    }

    public Boolean deleteMachine(Long machineId) {
        return null;
    }

}
