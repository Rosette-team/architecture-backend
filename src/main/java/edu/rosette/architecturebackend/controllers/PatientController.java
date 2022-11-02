package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.models.Patient;
import edu.rosette.architecturebackend.models.UserRole;
import edu.rosette.architecturebackend.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller()
@RequestMapping(value = "patient")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        var patientId = patientService.addPatient(patient);
        return new ResponseEntity<>(patientId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPatient(@PathVariable long id) {
        var securityContext = SecurityContextHolder.getContext();
        var test = securityContext.getAuthentication();

        var patient = patientService.getPatient(id);
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePatient(@PathVariable long id, @RequestBody Patient patientDto) {
        var patient = patientService.updatePatient(id, patientDto);
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
