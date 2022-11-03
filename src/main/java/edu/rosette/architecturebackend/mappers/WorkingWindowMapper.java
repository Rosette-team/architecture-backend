package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.datatransfer.WorkingWindowDto;
import edu.rosette.architecturebackend.models.WorkingWindow;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class WorkingWindowMapper {
    @Autowired
    DoctorRepository doctorRepository;

    public abstract WorkingWindow workingWindowDtoToWorkingWindow(WorkingWindowDto workingWindowDto);

    @AfterMapping
    protected void afterWorkingWindowDtoToWorkingWindow(WorkingWindowDto workingWindowDto, @MappingTarget WorkingWindow workingWindow) {
        var doctor = doctorRepository.findById(workingWindowDto.getDoctorId());
        if (doctor.isEmpty()) {
            throw new RuntimeException("Try to assign doctor with id %d but it's not exits".formatted(workingWindowDto.getDoctorId()));
        }
        workingWindow.setDoctor(doctor.get());
    }

    public abstract WorkingWindowDto workingWindowToWorkingWindowDto(WorkingWindow workingWindow);

    @AfterMapping
    protected void afterWorkingWindowToWorkingWindowDto(WorkingWindow workingWindow, @MappingTarget WorkingWindowDto workingWindowDto) {
        workingWindowDto.setDoctorId(workingWindow.getDoctor().getId());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract WorkingWindow updateWorkingWindowFromWorkingWindowDto(WorkingWindowDto workingWindowDto, @MappingTarget WorkingWindow workingWindow);
}
