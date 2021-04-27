package com.sofisticat.management.controllers;


import com.sofisticat.management.dto.EmployeeDto;
import com.sofisticat.management.entities.Employee;
import com.sofisticat.management.entities.Project;
import com.sofisticat.management.services.EmployeeService;
import com.sofisticat.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    final
    EmployeeService employeeService;

    final
    ProjectService projectService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        List<Project> allProjects = projectService.getAll();

        Employee employee = new Employee();
        model.addAttribute("allProjects", allProjects);
        model.addAttribute("employee", employee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String handleEmployeeForm(@Valid Employee employee, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employees/new-employee";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping
    public String getEmployees(Model model) {
        List<EmployeeDto> employeeListProjectCount = employeeService.getProjectStatus();
        model.addAttribute("employeeListProjectCount", employeeListProjectCount);
        return "employees/home";
    }

    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long id, Model model) {
        Employee byId = employeeService.getById(id);
        List<Project> projects = projectService.getAll();
        model.addAttribute("allProjects", projects);
        model.addAttribute("employee", byId);
        return "employees/new-employee";

    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id, Model model) {
        employeeService.deleteById(id);
        List<EmployeeDto> employeeListProjectCount = employeeService.getProjectStatus();
        model.addAttribute("employeeListProjectCount", employeeListProjectCount);
        return "redirect:/employees";
    }
}
