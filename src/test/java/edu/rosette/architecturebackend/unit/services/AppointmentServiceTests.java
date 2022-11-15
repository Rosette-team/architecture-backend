package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.AppointmentDto;
import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.datatransfer.PatientDto;
import edu.rosette.architecturebackend.mappers.AppointmentMapper;
import edu.rosette.architecturebackend.mappers.DoctorMapper;
import edu.rosette.architecturebackend.mappers.PatientMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.AppointmentRepository;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import edu.rosette.architecturebackend.services.AppointmentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AppointmentServiceTests {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    DoctorMapper doctorMapper;

    long patientId;
    long doctorId;

    @BeforeAll()
    void setupPatientAndDoctor() {
        patientId = patientRepository.save(patientMapper.patientDtoToPatient(new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT))).getId();
        doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, "speciality"))).getId();
    }

    @AfterAll()
    void clearPatientAndDoctor() {
        patientRepository.deleteAll();
        doctorRepository.deleteAll();
    }

    @AfterEach
    void cleanDatabase() {
        appointmentRepository.deleteAll();
    }

    @Test
    void canAddAppointment() {
        var appointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), false, null);

        var appointmentId = appointmentService.addAppointment(appointmentDto);

        Assertions.assertTrue(appointmentRepository.existsById(appointmentId));
    }

    @Test
    void canGetAppointment() {
        var appointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), false, null);
        var appointmentId = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(appointmentDto)).getId();

        var result = appointmentService.getAppointment(appointmentId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactAppointment() {
        var appointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), false, null);
        var appointmentId = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(appointmentDto)).getId();

        var result = appointmentService.getAppointment(appointmentId).orElseThrow();

        Assertions.assertEquals(result.getPatientId(), appointmentDto.getPatientId());
        Assertions.assertEquals(result.getDoctorId(), appointmentDto.getDoctorId());
        Assertions.assertEquals(result.getOnline(), appointmentDto.getOnline());
        Assertions.assertEquals(result.getConsultationLink(), appointmentDto.getConsultationLink());

    }

    @Test
    void canUpdateAppointment() {
        var oldAppointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), false, null);
        var newAppointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), true, "https://consultation.com");
        var appointmentId = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(oldAppointmentDto)).getId();

        var result = appointmentService.updateAppointment(appointmentId, newAppointmentDto);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canUpdateAppointmentCorrectly() {
        var oldAppointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), false, null);
        var newAppointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), true, "https://consultation.com");
        var appointmentId = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(oldAppointmentDto)).getId();

        var result = appointmentService.updateAppointment(appointmentId, newAppointmentDto).orElseThrow();

        Assertions.assertEquals(result.getOnline(), newAppointmentDto.getOnline());
        Assertions.assertEquals(result.getConsultationLink(), newAppointmentDto.getConsultationLink());
    }

    @Test
    void canDeleteAppointment() {
        var appointmentDto = new AppointmentDto(null, patientId, doctorId, new Date(), false, null);
        var appointmentId = appointmentRepository.save(appointmentMapper.appointmentDtoToAppointment(appointmentDto)).getId();

        appointmentService.deleteAppointment(appointmentId);

        Assertions.assertFalse(appointmentRepository.existsById(appointmentId));
    }
}
