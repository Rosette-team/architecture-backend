package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.AppointmentDto;
import edu.rosette.architecturebackend.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDto appointment) {
        var appointmentId = appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(appointmentId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAppointment(@PathVariable long id) {
        var appointment = appointmentService.getAppointment(id);
        if (appointment.isPresent()) {
            return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable long id, @RequestBody AppointmentDto appointmentDto) {
        var appointment = appointmentService.updateAppointment(id, appointmentDto);
        if (appointment.isPresent()) {
            return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
