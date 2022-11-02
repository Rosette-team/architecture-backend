package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.Patient;
import edu.rosette.architecturebackend.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public long addPatient(Patient patient) {
        return patientRepository.save(patient).getId();
    }

    public Optional<Patient> getPatient(long id) {
        return patientRepository.findById(id);
    }

    public Optional<Patient> updatePatient(long id, Patient patientDto) {
        var patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(patientRepository.save(patient.get()));
    }

    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }
}
