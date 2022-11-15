package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.AppointmentDto;
import edu.rosette.architecturebackend.mappers.AppointmentMapper;
import edu.rosette.architecturebackend.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper;


    public long addAppointment(AppointmentDto appointmentDto) {
        var appointment = appointmentMapper.appointmentDtoToAppointment(appointmentDto);
        return appointmentRepository.save(appointment).getId();
    }

    public Optional<AppointmentDto> getAppointment(long id) {
        var appointment =  appointmentRepository.findById(id);
        if (appointment.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(appointmentMapper.appointmentToAppointmentDto(appointment.get()));
    }

    public Optional<AppointmentDto> updateAppointment(long id, AppointmentDto appointmentDto) {
        var appointment = appointmentRepository.findById(id);
        if (appointment.isEmpty()) {
            return Optional.empty();
        }
        var updatedAppointment = appointmentMapper.updateAppointmentFromAppointmentDto(appointmentDto, appointment.get());
        return Optional.of(appointmentMapper.appointmentToAppointmentDto(appointmentRepository.save(updatedAppointment)));
    }

    public void deleteAppointment(long id) {
        appointmentRepository.deleteById(id);
    }
}
