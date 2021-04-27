package com.sofisticat.management.dao;

import com.sofisticat.management.dto.ProjectChartDto;
import com.sofisticat.management.dto.ProjectTimelineDto;
import com.sofisticat.management.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query(
            nativeQuery = true,
            value = "SELECT p.stage as label," +
                    "count(*) as count " +
                    "FROM project p " +
                    "GROUP BY p.stage;"
    )
    public List<ProjectChartDto> getProjectStatus();


    @Query(
            nativeQuery = true,
            value = "SELECT name as projectName,start_date as startDate,end_date as endDate " +
                    "FROM project"
    )
    public List<ProjectTimelineDto> getProjectTimeLine();
}
