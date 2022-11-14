package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.ManagerDto;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.services.ManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagerServiceTests {
    @Autowired
    ManagerService managerService;

    @Test
    void canAddManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER);

        Assertions.assertDoesNotThrow(() -> managerService.addManager(managerDto));
    }

}
