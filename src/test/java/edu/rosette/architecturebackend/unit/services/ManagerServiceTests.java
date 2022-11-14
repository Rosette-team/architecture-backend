package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.ManagerDto;
import edu.rosette.architecturebackend.mappers.ManagerMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.ManagerRepository;
import edu.rosette.architecturebackend.services.ManagerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagerServiceTests {
    @Autowired
    ManagerService managerService;

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    ManagerRepository managerRepository;

    @AfterEach
    void cleanDatabase() {
        managerRepository.deleteAll();
    }

    @Test
    void canAddManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER);

        Assertions.assertDoesNotThrow(() -> managerService.addManager(managerDto));
    }

    @Test
    void canGetManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(managerDto)).getId();

        var result = managerService.getManager(managerId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(managerDto)).getId();

        var result = managerService.getManager(managerId).orElseThrow();

        Assertions.assertEquals(result.getName(), managerDto.getName());
        Assertions.assertEquals(result.getSurname(), managerDto.getSurname());
        Assertions.assertEquals(result.getUsername(), managerDto.getUsername());
    }

    @Test
    void canUpdateManager() {
        var oldManagerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER);
        var newManagerDto = new ManagerDto(null, "newMame", "newSurname", "newUsername", "password", UserRole.ROLE_MANAGER);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(oldManagerDto)).getId();

        var result = managerService.updateManager(managerId, newManagerDto).orElseThrow();

        Assertions.assertEquals(result.getName(), newManagerDto.getName());
        Assertions.assertEquals(result.getSurname(), newManagerDto.getSurname());
        Assertions.assertEquals(result.getUsername(), newManagerDto.getUsername());
    }

    @Test
    void canDeleteManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(managerDto)).getId();

        managerService.deleteManager(managerId);

        Assertions.assertFalse(managerRepository.existsById(managerId));
    }
}
