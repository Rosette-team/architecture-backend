package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.datatransfer.PatientDto;
import edu.rosette.architecturebackend.mappers.PatientMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import edu.rosette.architecturebackend.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientMapper patientMapper;

    PatientDto patientDto = new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT);
    Long patientId;

    @BeforeAll()
    void setupPatient() {
        patientId = patientRepository.save(patientMapper.patientDtoToPatient(patientDto)).getId();
    }

    @AfterAll()
    void clearPatient() {
        patientRepository.deleteAll();
    }

    @Test
    @WithMockUser(username="patient", roles={"PATIENT"})
    void canGetAuthenticatedUserCorrectly() {
        var result = userService.getAuthenticatedUser();

        Assertions.assertEquals(result.getName(), patientDto.getName());
        Assertions.assertEquals(result.getSurname(), patientDto.getSurname());
    }
}
