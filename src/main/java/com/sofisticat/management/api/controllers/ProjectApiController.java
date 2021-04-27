package com.sofisticat.management.api.controllers;

import com.sofisticat.management.entities.Project;
import com.sofisticat.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projectService;

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") long id) {
        return projectService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public Project createProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project updateProject(@RequestBody Project project, @PathVariable String id) {
        if (project.getProjectId() == Long.parseLong(id)) {
            return projectService.save(project);
        }
        return null;
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project partialUpdateProject(@RequestBody Project project) {
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable("id") long id) {
        projectService.deleteById(id);
    }

}
