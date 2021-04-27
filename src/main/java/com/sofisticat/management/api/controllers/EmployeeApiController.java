package com.sofisticat.management.api.controllers;

import com.sofisticat.management.entities.Employee;
import com.sofisticat.management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable long id) {
        return employeeService.getById(id);
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Validated Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable String id) {
        if (employee.getEmployeeId() == Long.parseLong(id)) {
            return employeeService.save(employee);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteById(id);
    }

    //particular update
    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) throws ChangeSetPersister.NotFoundException {
        return employeeService.getById(id);
        //create a dto or a specific field holding places
        // to manage where to update
        // partial update
    }
}
