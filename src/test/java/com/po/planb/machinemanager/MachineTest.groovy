package com.po.planb.machinemanager

import com.po.planb.machinemanager.controller.MachineController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

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
}
