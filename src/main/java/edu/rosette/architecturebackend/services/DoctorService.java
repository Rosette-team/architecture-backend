package edu.rosette.architecturebackend.services;

import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.mappers.DoctorMapper;
import edu.rosette.architecturebackend.repositories.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DoctorService {
    DoctorRepository doctorRepository;

    DoctorMapper doctorMapper;

    public long addDoctor(DoctorDto doctorDto) {
        var doctor = doctorMapper.doctorDtoToDoctor(doctorDto);
        return doctorRepository.save(doctor).getId();
    }

    public Optional<DoctorDto> getDoctor(long id) {
        var doctor =  doctorRepository.findById(id);
        if (doctor.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(doctorMapper.doctorToDoctorDto(doctor.get()));
    }

    public List<DoctorDto> getDoctors(long departmentId) {
        var doctors = doctorRepository.findAllByDepartmentId(departmentId);
        return doctors.stream().map(doctor -> doctorMapper.doctorToDoctorDto(doctor)).toList();
    }

    public Optional<DoctorDto> updateDoctor(long id, DoctorDto doctorDto) {
        var doctor = doctorRepository.findById(id);
        if (doctor.isEmpty()) {
            return Optional.empty();
        }
        var updatedDoctor = doctorMapper.updateDoctorFromDoctorDto(doctorDto, doctor.get());
        return Optional.of(doctorMapper.doctorToDoctorDto(doctorRepository.save(updatedDoctor)));
    }

    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }
}
