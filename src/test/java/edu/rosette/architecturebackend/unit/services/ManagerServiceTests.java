package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.DepartmentDto;
import edu.rosette.architecturebackend.datatransfer.ManagerDto;
import edu.rosette.architecturebackend.mappers.DepartmentMapper;
import edu.rosette.architecturebackend.mappers.ManagerMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import edu.rosette.architecturebackend.repositories.ManagerRepository;
import edu.rosette.architecturebackend.services.ManagerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ManagerServiceTests {
    @Autowired
    ManagerService managerService;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    Long departmentId;

    @BeforeAll()
    void setupDepartment() {
        departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(new DepartmentDto(null, "department"))).getId();
    }

    @AfterAll()
    void clearDepartment() {
        departmentRepository.deleteAll();
    }

    @AfterEach
    void clearDatabase() {
        managerRepository.deleteAll();
    }

    @Test
    void canAddManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER, departmentId);

        var managerId = managerService.addManager(managerDto);

        Assertions.assertTrue(managerRepository.existsById(managerId));
    }

    @Test
    void canGetManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER, departmentId);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(managerDto)).getId();

        var result = managerService.getManager(managerId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER, departmentId);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(managerDto)).getId();

        var result = managerService.getManager(managerId).orElseThrow();

        Assertions.assertEquals(result.getName(), managerDto.getName());
        Assertions.assertEquals(result.getSurname(), managerDto.getSurname());
        Assertions.assertEquals(result.getUsername(), managerDto.getUsername());
    }

    @Test
    void canGetFilteredManagers() {
        var managerDto1 = new ManagerDto(null, "name1", "surname1", "username1", "password1", UserRole.ROLE_MANAGER, departmentId);
        var managerDto2 = new ManagerDto(null, "name2", "surname2", "username2", "password2", UserRole.ROLE_MANAGER, null);
        managerRepository.save(managerMapper.managerDtoToManager(managerDto1));
        managerRepository.save(managerMapper.managerDtoToManager(managerDto2));

        var managers = managerService.getManagers(departmentId);

        Assertions.assertTrue(managers.stream().anyMatch((managerDto -> Objects.equals(managerDto.getName(), managerDto1.getName()))));
        Assertions.assertTrue(managers.stream().noneMatch((managerDto -> Objects.equals(managerDto.getName(), managerDto2.getName()))));
    }

    @Test
    void canUpdateManager() {
        var oldManagerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER, departmentId);
        var newManagerDto = new ManagerDto(null, "newMame", "newSurname", "newUsername", "password", UserRole.ROLE_MANAGER, departmentId);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(oldManagerDto)).getId();

        var result = managerService.updateManager(managerId, newManagerDto);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canUpdateManagerCorrectly() {
        var oldManagerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER, departmentId);
        var newManagerDto = new ManagerDto(null, "newMame", "newSurname", "newUsername", "password", UserRole.ROLE_MANAGER, departmentId);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(oldManagerDto)).getId();

        var result = managerService.updateManager(managerId, newManagerDto).orElseThrow();

        Assertions.assertEquals(result.getName(), newManagerDto.getName());
        Assertions.assertEquals(result.getSurname(), newManagerDto.getSurname());
        Assertions.assertEquals(result.getUsername(), newManagerDto.getUsername());
    }

    @Test
    void canDeleteManager() {
        var managerDto = new ManagerDto(null, "name", "surname", "username", "password", UserRole.ROLE_MANAGER, departmentId);
        var managerId = managerRepository.save(managerMapper.managerDtoToManager(managerDto)).getId();

        managerService.deleteManager(managerId);

        Assertions.assertFalse(managerRepository.existsById(managerId));
    }
}
