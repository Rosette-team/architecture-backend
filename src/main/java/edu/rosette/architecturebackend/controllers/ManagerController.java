package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.ManagerDto;
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
    public ResponseEntity<?> addManager(@RequestBody ManagerDto managerDto) {
        var managerId = managerService.addManager(managerDto);
        return new ResponseEntity<>(managerId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getManager(@PathVariable long id) {
        var managerDto = managerService.getManager(id);
        if (managerDto.isPresent()) {
            return new ResponseEntity<>(managerDto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getManagers() {
        var managers = managerService.getManagers(null);
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateManager(@PathVariable long id, @RequestBody ManagerDto managerDto) {
        var manager = managerService.updateManager(id, managerDto);
        if (manager.isPresent()) {
            return new ResponseEntity<>(manager.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteManager(@PathVariable long id) {
        managerService.deleteManager(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
