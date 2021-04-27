package com.sofisticat.management.services;


import com.sofisticat.management.dao.ProjectRepository;
import com.sofisticat.management.dto.ProjectChartDto;
import com.sofisticat.management.dto.ProjectTimelineDto;
import com.sofisticat.management.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public Project save(Project Project) {
        return projectRepository.save(Project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<ProjectChartDto> getProjectStatus() {
        return projectRepository.getProjectStatus();
    }

    public Project getById(long id) {
        return projectRepository.findById(id).get();
    }

    public void deleteById(long id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectTimelineDto> getProjectTimeline() {
        return projectRepository.getProjectTimeLine();
    }
}
