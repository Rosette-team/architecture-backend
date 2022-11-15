package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.DepartmentDto;
import edu.rosette.architecturebackend.mappers.DepartmentMapper;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import edu.rosette.architecturebackend.services.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentServiceTests {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentMapper departmentMapper;

    @AfterEach
    void clearDatabase() {
        departmentRepository.deleteAll();
    }

    @Test
    void canAddDepartment() {
        var departmentDto = new DepartmentDto(null, "department");

        var departmentId = departmentService.addDepartment(departmentDto);

        Assertions.assertTrue(departmentRepository.existsById(departmentId));
    }

    @Test
    void canGetDepartment() {
        var departmentDto = new DepartmentDto(null, "department");
        var departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(departmentDto)).getId();

        var result = departmentService.getDepartment(departmentId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactDepartment() {
        var departmentDto = new DepartmentDto(null, "department");
        var departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(departmentDto)).getId();

        var result = departmentService.getDepartment(departmentId).orElseThrow();

        Assertions.assertEquals(result.getName(), departmentDto.getName());
    }

    @Test
    void canUpdateDepartment() {
        var oldDepartmentDto = new DepartmentDto(null, "department");
        var newDepartmentDto = new DepartmentDto(null, "newDepartment");
        var departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(oldDepartmentDto)).getId();

        var result = departmentService.updateDepartment(departmentId, newDepartmentDto);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canUpdateDepartmentCorrectly() {
        var oldDepartmentDto = new DepartmentDto(null, "department");
        var newDepartmentDto = new DepartmentDto(null, "newDepartment");
        var departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(oldDepartmentDto)).getId();

        var result = departmentService.updateDepartment(departmentId, newDepartmentDto).orElseThrow();

        Assertions.assertEquals(result.getName(), newDepartmentDto.getName());
    }

    @Test
    void canDeleteDepartment() {
        var departmentDto = new DepartmentDto(null, "department");
        var departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(departmentDto)).getId();

        departmentService.deleteDepartment(departmentId);

        Assertions.assertFalse(departmentRepository.existsById(departmentId));
    }
}
