package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
