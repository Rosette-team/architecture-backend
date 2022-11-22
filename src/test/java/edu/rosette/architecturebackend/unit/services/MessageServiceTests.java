package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.datatransfer.MessageDto;
import edu.rosette.architecturebackend.datatransfer.PatientDto;
import edu.rosette.architecturebackend.mappers.DoctorMapper;
import edu.rosette.architecturebackend.mappers.MessageMapper;
import edu.rosette.architecturebackend.mappers.PatientMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import edu.rosette.architecturebackend.repositories.MessageRepository;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import edu.rosette.architecturebackend.services.MessageService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Date;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MessageServiceTests {
    @Autowired
    MessageService messageService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    DoctorMapper doctorMapper;

    long patientId;
    long doctorId;

    @BeforeAll()
    void setupPatientAndDoctor() {
        patientId = patientRepository.save(patientMapper.patientDtoToPatient(new PatientDto(null, "name", "surname", "patient", "password", UserRole.ROLE_PATIENT))).getId();
        doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, null, "speciality"))).getId();
    }

    @AfterAll()
    void clearPatientAndDoctor() {
        patientRepository.deleteAll();
        doctorRepository.deleteAll();
    }

    @AfterEach
    void clearDatabase() {
        messageRepository.deleteAll();
    }

    @Test
    void canAddMessage() {
        var messageDto = new MessageDto(null, patientId, doctorId, new Date(), "text");

        var messageId = messageService.addMessage(messageDto);

        Assertions.assertTrue(messageRepository.existsById(messageId));
    }

    @Test
    @WithMockUser(username="patient", roles={"PATIENT"})
    void canGetExactMessages() {
        var firstMessageDto = new MessageDto(null, patientId, doctorId, new Date(), "firstText");
        var secondMessageDto = new MessageDto(null, doctorId, patientId, new Date(), "secondText");
        messageRepository.save(messageMapper.messageDtoToMessage(firstMessageDto));
        messageRepository.save(messageMapper.messageDtoToMessage(secondMessageDto));

        var messages = messageService.getAllMessagesBetweenAuthenticatedUserAndUser(doctorId);

        Assertions.assertTrue(messages.stream().anyMatch((messageDto -> Objects.equals(messageDto.getText(), firstMessageDto.getText()))));
        Assertions.assertTrue(messages.stream().anyMatch((messageDto -> Objects.equals(messageDto.getText(), secondMessageDto.getText()))));
    }

}
