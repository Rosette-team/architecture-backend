package edu.rosette.architecturebackend.controllers;

import edu.rosette.architecturebackend.datatransfer.DepartmentDto;
import edu.rosette.architecturebackend.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto) {
        var departmentId = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(departmentId, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDepartment(@PathVariable long id) {
        var department = departmentService.getDepartment(id);
        if (department.isPresent()) {
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getDepartments() {
        var departments = departmentService.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable long id, @RequestBody DepartmentDto departmentDto) {
        var department = departmentService.updateDepartment(id, departmentDto);
        if (department.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
