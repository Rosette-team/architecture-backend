package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
