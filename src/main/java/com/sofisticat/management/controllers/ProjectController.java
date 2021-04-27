package com.sofisticat.management.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofisticat.management.dto.EmployeeChartData;
import com.sofisticat.management.dto.ProjectChartDto;
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

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    EmployeeService employeeService;


    @Autowired
    ProjectService projectService;

    @GetMapping
    public String getProjects(Model model) throws JsonProcessingException {
        try {
            pieChartDataExtractor(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(model.getAttribute("employeeChartData"));
        return "projects/home";
    }


    @GetMapping("/new")
    public String displayFormProject(Model model) {

        try {
            List<Employee> allEmployees = employeeService.getAll();
            model.addAttribute("allEmployees", allEmployees);
        } catch (Exception e) {
            System.out.println("Nothing to show");
        }
        Project project = new Project();
        model.addAttribute("project", project);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createNewProject(Project project, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "projects/new-project";
        }
        projectService.save(project);
        // use redirect to prevent dublicates
        return "redirect:/projects";
    }

    @GetMapping("/update")
    public String handleUpdateFormRequest(@RequestParam("id") long id, Model model) {
        model.addAttribute("allEmployees", employeeService.getAll());
        model.addAttribute("project", projectService.getById(id));
        return "projects/new-project";
    }

    @GetMapping("/delete")
    public String handleDeleteRequest(@RequestParam("id") long id, Model model) {
        pieChartDataExtractor(model);
        projectService.deleteById(id);
        return "redirect:/projects";
    }

    private void pieChartDataExtractor(Model model) {
        List<Project> projects = projectService.getAll();

        model.addAttribute("projects", projects);

        // json format data bind
        try {
            List<ProjectChartDto> projectStatus = projectService.getProjectStatus();
            ObjectMapper objectMapper = new ObjectMapper();
            model.addAttribute("projectStatusCount", objectMapper.writeValueAsString(projectStatus));
            model.addAttribute("projectTimeline", objectMapper.writeValueAsString(projectService.getProjectTimeline()));
        } catch (Exception e) {
            System.out.println("No valid data");
        }
    }
//
//    @GetMapping("/")
//    public String projectTimelines(){
//
//    }

}
