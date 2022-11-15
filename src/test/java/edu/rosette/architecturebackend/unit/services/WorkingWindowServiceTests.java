package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.datatransfer.WorkingWindowDto;
import edu.rosette.architecturebackend.mappers.DoctorMapper;
import edu.rosette.architecturebackend.mappers.WorkingWindowMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import edu.rosette.architecturebackend.repositories.WorkingWindowRepository;
import edu.rosette.architecturebackend.services.WorkingWindowService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class WorkingWindowServiceTests {
    @Autowired
    WorkingWindowService workingWindowService;

    @Autowired
    WorkingWindowRepository workingWindowRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    WorkingWindowMapper workingWindowMapper;

    @Autowired
    DoctorMapper doctorMapper;

    long firstDoctorId;
    long secondDoctorId;

    @BeforeAll()
    void setupDoctor() {
        firstDoctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(new DoctorDto(null, "name", "surname", "firstDoctor", "password", UserRole.ROLE_DOCTOR, "speciality"))).getId();
        secondDoctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(new DoctorDto(null, "name", "surname", "secondDoctor", "password", UserRole.ROLE_DOCTOR, "speciality"))).getId();
    }

    @AfterAll()
    void clearDoctor() {
        doctorRepository.deleteAll();
    }

    @AfterEach
    void cleanDatabase() {
        workingWindowRepository.deleteAll();
    }

    @Test
    void canAddWorkingWindow() {
        var workingWindowDto = new WorkingWindowDto(null, firstDoctorId, new Date(), new Date(), "", Duration.ZERO);

        var workingWindowId = workingWindowService.addWorkingWindow(workingWindowDto);

        Assertions.assertTrue(workingWindowRepository.existsById(workingWindowId));
    }

    @Test
    void canGetWorkingWindow() {
        var workingWindowDto = new WorkingWindowDto(null, firstDoctorId, new Date(), new Date(), "", Duration.ZERO);
        var workingWindowId = workingWindowRepository.save(workingWindowMapper.workingWindowDtoToWorkingWindow(workingWindowDto)).getId();

        var result = workingWindowService.getWorkingWindow(workingWindowId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactWorkingWindow() {
        var workingWindowDto = new WorkingWindowDto(null, firstDoctorId, new Date(), new Date(), "", Duration.ZERO);
        var workingWindowId = workingWindowRepository.save(workingWindowMapper.workingWindowDtoToWorkingWindow(workingWindowDto)).getId();

        var result = workingWindowService.getWorkingWindow(workingWindowId).orElseThrow();

        Assertions.assertEquals(result.getDoctorId(), workingWindowDto.getDoctorId());
    }

    @Test
    void canUpdateWorkingWindow() {
        var oldWorkingWindowDto = new WorkingWindowDto(null, firstDoctorId, new Date(), new Date(), "", Duration.ZERO);
        var newWorkingWindowDto = new WorkingWindowDto(null, secondDoctorId, null, null, null, null);
        var workingWindowId = workingWindowRepository.save(workingWindowMapper.workingWindowDtoToWorkingWindow(oldWorkingWindowDto)).getId();

        var result = workingWindowService.updateWorkingWindow(workingWindowId, newWorkingWindowDto);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canUpdateWorkingWindowCorrectly() {
        var oldWorkingWindowDto = new WorkingWindowDto(null, firstDoctorId, new Date(), new Date(), "", Duration.ZERO);
        var newWorkingWindowDto = new WorkingWindowDto(null, secondDoctorId, null, null, null, null);
        var workingWindowId = workingWindowRepository.save(workingWindowMapper.workingWindowDtoToWorkingWindow(oldWorkingWindowDto)).getId();

        var result = workingWindowService.updateWorkingWindow(workingWindowId, newWorkingWindowDto).orElseThrow();

        Assertions.assertEquals(result.getDoctorId(), newWorkingWindowDto.getDoctorId());
    }

    @Test
    void canDeleteWorkingWindow() {
        var workingWindowDto = new WorkingWindowDto(null, firstDoctorId, new Date(), new Date(), "", Duration.ZERO);
        var workingWindowId = workingWindowRepository.save(workingWindowMapper.workingWindowDtoToWorkingWindow(workingWindowDto)).getId();

        workingWindowService.deleteWorkingWindow(workingWindowId);

        Assertions.assertFalse(workingWindowRepository.existsById(workingWindowId));
    }
}
