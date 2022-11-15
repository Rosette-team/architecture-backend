package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.PatientDto;
import edu.rosette.architecturebackend.mappers.PatientMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import edu.rosette.architecturebackend.services.PatientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTests {
    @Autowired
    PatientService patientService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientMapper patientMapper;

    @AfterEach
    void clearDatabase() {
        patientRepository.deleteAll();
    }

    @Test
    void canAddPatient() {
        var patientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);

        var patientId = patientService.addPatient(patientDto);

        Assertions.assertTrue(patientRepository.existsById(patientId));
    }

    @Test
    void canGetPatient() {
        var patientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);
        var patientId = patientRepository.save(patientMapper.patientDtoToPatient(patientDto)).getId();

        var result = patientService.getPatient(patientId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactPatient() {
        var patientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);
        var patientId = patientRepository.save(patientMapper.patientDtoToPatient(patientDto)).getId();

        var result = patientService.getPatient(patientId).orElseThrow();

        Assertions.assertEquals(result.getName(), patientDto.getName());
        Assertions.assertEquals(result.getSurname(), patientDto.getSurname());
        Assertions.assertEquals(result.getUsername(), patientDto.getUsername());
    }

    @Test
    void canUpdatePatient() {
        var oldPatientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);
        var newPatientDto = new PatientDto(null, "newName", "newSurname", "newPatient", "newPassword", UserRole.ROLE_PATIENT);
        var patientId = patientRepository.save(patientMapper.patientDtoToPatient(oldPatientDto)).getId();

        var result = patientService.updatePatient(patientId, newPatientDto);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canUpdatePatientCorrectly() {
        var oldPatientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);
        var newPatientDto = new PatientDto(null, "newName", "newSurname", "newPatient", "newPassword", UserRole.ROLE_PATIENT);
        var patientId = patientRepository.save(patientMapper.patientDtoToPatient(oldPatientDto)).getId();

        var result = patientService.updatePatient(patientId, newPatientDto).orElseThrow();

        Assertions.assertEquals(result.getName(), newPatientDto.getName());
        Assertions.assertEquals(result.getSurname(), newPatientDto.getSurname());
        Assertions.assertEquals(result.getUsername(), newPatientDto.getUsername());
    }

    @Test
    void canDeletePatient() {
        var patientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);
        var patientId = patientRepository.save(patientMapper.patientDtoToPatient(patientDto)).getId();

        patientService.deletePatient(patientId);

        Assertions.assertFalse(patientRepository.existsById(patientId));
    }
}
