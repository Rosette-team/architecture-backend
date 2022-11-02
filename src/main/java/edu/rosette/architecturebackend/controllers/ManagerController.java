package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.models.Manager;
import edu.rosette.architecturebackend.services.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller()
@RequestMapping(value = "manager")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<?> addManager(@RequestBody Manager manager) {
        var managerId = managerService.addManager(manager);
        return new ResponseEntity<>(managerId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getManager(@PathVariable long id) {
        var manager = managerService.getManager(id);
        if (manager.isPresent()) {
            return new ResponseEntity<>(manager.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateManager(@PathVariable long id, @RequestBody Manager managerDto) {
        var manager = managerService.updateManager(id, managerDto);
        if (manager.isPresent()) {
            return new ResponseEntity<>(manager.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteManager(@PathVariable long id) {
        managerService.deleteManager(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
