package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.models.Appointment;
import edu.rosette.architecturebackend.datatransfer.AppointmentDto;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AppointmentMapper {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    public abstract Appointment appointmentDtoToAppointment(AppointmentDto appointmentDto);

    @AfterMapping
    protected void afterAppointmentDtoToAppointment(AppointmentDto appointmentDto, @MappingTarget Appointment appointment) {
        var doctor = doctorRepository.findById(appointmentDto.getDoctorId());
        if (doctor.isEmpty()) {
            throw new RuntimeException("Try to assign doctor with id %d but it's not exits".formatted(appointmentDto.getDoctorId()));
        }
        var patient = patientRepository.findById(appointmentDto.getPatientId());
        if (patient.isEmpty()) {
            throw new RuntimeException("Try to assign patient with id %d but it's not exits".formatted(appointmentDto.getPatientId()));
        }
        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
    }

    @InheritInverseConfiguration(name = "appointmentDtoToAppointment")
    public abstract AppointmentDto appointmentToAppointmentDto(Appointment appointment);

    @AfterMapping
    protected void afterAppointmentToAppointmentDto(Appointment appointment, @MappingTarget AppointmentDto appointmentDto) {
        appointmentDto.setDoctorId(appointment.getDoctor().getId());
        appointmentDto.setPatientId(appointment.getPatient().getId());
    }

    @InheritConfiguration(name = "appointmentDtoToAppointment")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Appointment updateAppointmentFromAppointmentDto(AppointmentDto appointmentDto, @MappingTarget Appointment appointment);
}
