package com.po.planb.machinemanager;

import com.po.planb.machinemanager.repository.ManagementRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class MachineManagerApplication {

    @Autowired
    ManagementRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MachineManagerApplication.class, args);
    }

}
