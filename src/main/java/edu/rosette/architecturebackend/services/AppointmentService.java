package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.Appointment;
import edu.rosette.architecturebackend.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public long addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment).getId();
    }

    public Optional<Appointment> getAppointment(long id) {
        return appointmentRepository.findById(id);
    }

    public Optional<Appointment> updateAppointment(long id, Appointment appointmentDto) {
        var appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(appointmentRepository.save(appointment.get()));
    }

    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }
}
