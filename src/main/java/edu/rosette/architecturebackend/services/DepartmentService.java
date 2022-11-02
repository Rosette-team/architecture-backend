package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.DepartmentDto;
import edu.rosette.architecturebackend.mappers.DepartmentMapper;
import edu.rosette.architecturebackend.models.Department;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    public long addDepartment(DepartmentDto departmentDto) {
        var department = departmentMapper.departmentDtoToDepartment(departmentDto);
        return departmentRepository.save(department).getId();
    }

    public Optional<DepartmentDto> getDepartment(long id) {
        var department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(departmentMapper.departmentToDepartmentDto(department.get()));
    }

    public Optional<DepartmentDto> updateDepartment(long id, DepartmentDto departmentDto){
        var department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            return Optional.empty();
        }
        var updatedDepartment = departmentMapper.updateDepartmentFromDepartmentDto(departmentDto, department.get());
        return Optional.of(departmentMapper.departmentToDepartmentDto(departmentRepository.save(updatedDepartment)));
    }

    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }
}
