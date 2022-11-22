package edu.rosette.architecturebackend.unit.services;

import edu.rosette.architecturebackend.datatransfer.DepartmentDto;
import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.mappers.DepartmentMapper;
import edu.rosette.architecturebackend.mappers.DoctorMapper;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import edu.rosette.architecturebackend.services.DoctorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class DoctorServiceTests {
    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    Long departmentId;

    @BeforeAll()
    void setupDepartment() {
        departmentId = departmentRepository.save(departmentMapper.departmentDtoToDepartment(new DepartmentDto(null, "department"))).getId();
    }

    @AfterAll()
    void clearDepartment() {
        departmentRepository.deleteAll();
    }

    @AfterEach
    void clearDatabase() {
        doctorRepository.deleteAll();
    }

    @Test
    void canAddDoctor() {
        var doctorDto = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");

        var doctorId = doctorService.addDoctor(doctorDto);

        Assertions.assertTrue(doctorRepository.existsById(doctorId));
    }

    @Test
    void canGetDoctor() {
        var doctorDto = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");
        var doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(doctorDto)).getId();

        var result = doctorService.getDoctor(doctorId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canGetExactDoctor() {
        var doctorDto = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");
        var doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(doctorDto)).getId();

        var result = doctorService.getDoctor(doctorId).orElseThrow();

        Assertions.assertEquals(result.getName(), doctorDto.getName());
        Assertions.assertEquals(result.getSurname(), doctorDto.getSurname());
        Assertions.assertEquals(result.getUsername(), doctorDto.getUsername());
        Assertions.assertEquals(result.getSpeciality(), doctorDto.getSpeciality());
    }

    @Test
    void canGetFilteredDoctors() {
        var doctorDto1 = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");
        var doctorDto2 = new DoctorDto(null, "name2", "surname2", "doctor2", "password2", UserRole.ROLE_DOCTOR, null,"speciality");
        doctorRepository.save(doctorMapper.doctorDtoToDoctor(doctorDto1));
        doctorRepository.save(doctorMapper.doctorDtoToDoctor(doctorDto2));

        var doctors = doctorService.getDoctors(departmentId);

        Assertions.assertTrue(doctors.stream().anyMatch((doctorDto -> Objects.equals(doctorDto.getName(), doctorDto1.getName()))));
        Assertions.assertTrue(doctors.stream().noneMatch((doctorDto -> Objects.equals(doctorDto.getName(), doctorDto2.getName()))));
    }

    @Test
    void canUpdateDoctor() {
        var oldDoctorDto = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");
        var newDoctorDto = new DoctorDto(null, "newName", "newSurname", "newDoctor", "password", UserRole.ROLE_DOCTOR, departmentId,"newSpeciality");
        var doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(oldDoctorDto)).getId();

        var result = doctorService.updateDoctor(doctorId, newDoctorDto);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void canUpdateDoctorCorrectly() {
        var oldDoctorDto = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");
        var newDoctorDto = new DoctorDto(null, "newName", "newSurname", "newDoctor", "password", UserRole.ROLE_DOCTOR, departmentId,"newSpeciality");
        var doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(oldDoctorDto)).getId();

        var result = doctorService.updateDoctor(doctorId, newDoctorDto).orElseThrow();

        Assertions.assertEquals(result.getName(), newDoctorDto.getName());
        Assertions.assertEquals(result.getSurname(), newDoctorDto.getSurname());
        Assertions.assertEquals(result.getUsername(), newDoctorDto.getUsername());
        Assertions.assertEquals(result.getSpeciality(), newDoctorDto.getSpeciality());
    }

    @Test
    void canDeleteDoctor() {
        var doctorDto = new DoctorDto(null, "name", "surname", "doctor", "password", UserRole.ROLE_DOCTOR, departmentId,"speciality");
        var doctorId = doctorRepository.save(doctorMapper.doctorDtoToDoctor(doctorDto)).getId();

        doctorService.deleteDoctor(doctorId);

        Assertions.assertFalse(doctorRepository.existsById(doctorId));
    }
}
