package com.po.planb.machinemanager;

import com.po.planb.machinemanager.model.form.MachineForm;
import com.po.planb.machinemanager.service.impl.ManagementServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComputationTaskJunitTest {

    @Autowired
    private ManagementServiceImpl machineService;

    @Test
    public void getMachines() {
//        Assert.assertNotNull(machineService.getMachines(123L));
    }

    @Test
    public void getNonEmptyListOfMachines() {
//        Assert.assertFalse(machineService.getMachines(10L).isEmpty());
    }

//    @Test
//    public void createMachine() {
//        MachineForm m = new MachineForm(1,2,3,4);
//        Assert.assertTrue(machineService.createMachine(m));
//    }
}
