package edu.rosette.architecturebackend.repositories;

import edu.rosette.architecturebackend.models.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findAllByDoctorIdAndDate(Long id, LocalDate date);
}
