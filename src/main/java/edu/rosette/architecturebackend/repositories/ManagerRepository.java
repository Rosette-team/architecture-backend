package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Doctor;
import edu.rosette.architecturebackend.models.Manager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
    List<Manager> findAllByDepartmentId(long id);
}
