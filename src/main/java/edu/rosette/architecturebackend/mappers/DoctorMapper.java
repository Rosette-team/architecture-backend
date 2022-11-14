package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.models.Doctor;
import edu.rosette.architecturebackend.repositories.DepartmentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class DoctorMapper {
    @Autowired
    DepartmentRepository departmentRepository;
    public abstract Doctor doctorDtoToDoctor(DoctorDto doctorDto);

    @AfterMapping
    protected void afterDoctorDtoToDoctor(DoctorDto doctorDto, @MappingTarget Doctor doctor) {
        if (doctorDto.getDepartmentId() == null) {
            doctor.setDepartment(null);
            return;
        }
        var department = departmentRepository.findById(doctorDto.getDepartmentId());
        if (department.isEmpty()) {
            throw new RuntimeException("Try to assign department with id %d but it's not exits".formatted(doctorDto.getDepartmentId()));
        }
        doctor.setDepartment(department.get());
    }

    public abstract DoctorDto doctorToDoctorDto(Doctor doctor);

    @AfterMapping
    protected void afterDoctorToDoctorDto(Doctor doctor, @MappingTarget DoctorDto doctorDto) {
        if (doctor.getDepartment() == null) {
            return;
        }
        doctorDto.setDepartmentId(doctor.getDepartment().getId());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Doctor updateDoctorFromDoctorDto(DoctorDto doctorDto, @MappingTarget Doctor doctor);
}
