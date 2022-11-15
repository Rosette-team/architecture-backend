package edu.rosette.architecturebackend.mappers;

import edu.rosette.architecturebackend.models.Patient;
import edu.rosette.architecturebackend.datatransfer.PatientDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PatientMapper {
    Patient patientDtoToPatient(PatientDto patientDto);

    PatientDto patientToPatientDto(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient updatePatientFromPatientDto(PatientDto patientDto, @MappingTarget Patient patient);
}
