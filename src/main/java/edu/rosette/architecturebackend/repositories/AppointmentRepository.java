package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
