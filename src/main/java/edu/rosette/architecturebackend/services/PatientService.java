package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.PatientDto;
import edu.rosette.architecturebackend.mappers.PatientMapper;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public long addPatient(PatientDto patientDto) {
        var patient = patientMapper.patientDtoToPatient(patientDto);
        return patientRepository.save(patient).getId();
    }

    public Optional<PatientDto> getPatient(long id) {
        var patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(patientMapper.patientToPatientDto(patient.get()));
    }

    public Optional<PatientDto> updatePatient(long id, PatientDto patientDto) {
        var patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            return Optional.empty();
        }
        var updatedPatient = patientMapper.updatePatientFromPatientDto(patientDto, patient.get());
        return Optional.of(patientMapper.patientToPatientDto(patientRepository.save(updatedPatient)));
    }

    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }
}
