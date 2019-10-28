package com.po.planb.machinemanager;

//import com.po.planb.machinemanager.model.Customer;
import com.po.planb.machinemanager.model.Machine;
import com.po.planb.machinemanager.model.Parameters;
import com.po.planb.machinemanager.repository.MachineRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class MachineManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MachineManagerApplication.class, args);
    }
}
