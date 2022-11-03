package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    List<Doctor> findAllByDepartmentId(long id);
}
