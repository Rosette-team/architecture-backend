package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
