package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.DoctorDto;
import edu.rosette.architecturebackend.services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller()
@RequestMapping(value = "doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody DoctorDto doctorDto) {
        var doctorId = doctorService.addDoctor(doctorDto);
        return new ResponseEntity<>(doctorId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDoctor(@PathVariable long id) {
        var doctorDto = doctorService.getDoctor(id);
        if (doctorDto.isPresent()) {
            return new ResponseEntity<>(doctorDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getDoctors(@RequestParam(required = false) Long departmentId) {
        var doctors = doctorService.getDoctors(departmentId);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable long id, @RequestBody DoctorDto doctorDto) {
        var doctor = doctorService.updateDoctor(id, doctorDto);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

