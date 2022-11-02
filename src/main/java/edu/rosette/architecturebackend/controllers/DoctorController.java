package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.models.Doctor;
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
    public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
        var doctorId = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctorId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDoctor(@PathVariable long id) {
        var doctor = doctorService.getDoctor(id);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDoctor(@PathVariable long id, @RequestBody Doctor doctorDto) {
        var doctor = doctorService.updateDoctor(id, doctorDto);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

