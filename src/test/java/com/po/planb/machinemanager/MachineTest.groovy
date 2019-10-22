package com.po.planb.machinemanager

import com.po.planb.machinemanager.controller.MachineController
import com.po.planb.machinemanager.model.Machine
import com.po.planb.machinemanager.model.Parameters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.lang.reflect.Parameter

@SpringBootTest
class MachineTest extends Specification {

    @Autowired(required = false)
    private MachineController machineController

    def "when context is loaded then all expected beans are created"() {
        expect:
        "the WebController is created"
        machineController
    }

    def "show machine list"() {
        when:
        def result = machineController.getMachines()

        then:
        result == Collections.emptyList()
    }

    def "Create new Machine"(){
        when:
        def machine = new Machine(new Long(123), new Long(321), new Parameters(1,2), new Parameters(1,2), new Parameters(1,3), new Parameters(1,4))

        then:
        machine != null

    }



}
