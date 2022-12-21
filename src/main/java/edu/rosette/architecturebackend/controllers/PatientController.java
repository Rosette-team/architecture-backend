package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.PatientDto;
import edu.rosette.architecturebackend.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller()
@RequestMapping(value = "patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody PatientDto patientDto) {
        var patientId = patientService.addPatient(patientDto);
        return new ResponseEntity<>(patientId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPatient(@PathVariable long id) {
        var patientDto = patientService.getPatient(id);
        if (patientDto.isPresent()) {
            return new ResponseEntity<>(patientDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePatient(@PathVariable long id, @RequestBody PatientDto patientDto) {
        var patient = patientService.updatePatient(id, patientDto);
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
