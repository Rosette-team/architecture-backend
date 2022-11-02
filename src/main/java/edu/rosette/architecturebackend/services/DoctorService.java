package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.models.Doctor;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DoctorService {
    DoctorRepository doctorRepository;

    public long addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor).getId();
    }

    public Optional<Doctor> getDoctor(long id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> updateDoctor(long id, Doctor doctorDto) {
        var doctor = doctorRepository.findById(id);
        if (doctor.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(doctorRepository.save(doctor.get()));
    }

    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }
}
