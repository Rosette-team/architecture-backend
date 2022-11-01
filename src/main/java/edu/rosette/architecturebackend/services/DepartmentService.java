package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.Department;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public long addDepartment(Department department) {
        return departmentRepository.save(department).getId();
    }

    public Optional<Department> getDepartment(long id) {
        return departmentRepository.findById(id);
    }

    public Optional<Department> updateDepartment(long id, Department departmentDto){
        var department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(departmentRepository.save(department.get()));
    }

    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }
}
