package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.WorkingWindowDto;
import edu.rosette.architecturebackend.services.WorkingWindowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("working-window")
public class WorkingWindowController {

    private final WorkingWindowService workingWindowService;

    @PostMapping
    public ResponseEntity<?> addWorkingWindow(@RequestBody WorkingWindowDto workingWindowDto) {
        var workingWindowId = workingWindowService.addWorkingWindow(workingWindowDto);
        return new ResponseEntity<>(workingWindowId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getWorkingWindow(@PathVariable long id) {
        var securityContext = SecurityContextHolder.getContext();
        var test = securityContext.getAuthentication();

        var workingWindow = workingWindowService.getWorkingWindow(id);
        if (workingWindow.isPresent()) {
            return new ResponseEntity<>(workingWindow.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateWorkingWindow(@PathVariable long id, @RequestBody WorkingWindowDto workingWindowDto) {
        var workingWindow = workingWindowService.updateWorkingWindow(id, workingWindowDto);
        if (workingWindow.isPresent()) {
            return new ResponseEntity<>(workingWindow.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteWorkingWindow(@PathVariable long id) {
        workingWindowService.deleteWorkingWindow(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
