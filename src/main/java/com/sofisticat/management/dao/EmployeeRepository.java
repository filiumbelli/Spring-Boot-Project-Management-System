package com.sofisticat.management.dao;


import com.sofisticat.management.dto.EmployeeChartData;
import com.sofisticat.management.dto.EmployeeDto;
import com.sofisticat.management.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true,
            value = "SELECT e.first_name as firstName," +
                    "e.last_name as lastName," +
                    "e.employee_id as employeeId, " +
                    "count(e.employee_id) as projectCount " +
                    "FROM employee e " +
                    "LEFT JOIN project_employee pe " +
                    "ON ( pe.employee_id = e.employee_id) " +
                    "GROUP BY first_name,last_name,employeeId " +
                    "ORDER BY 3 DESC")
    public List<EmployeeDto> employeeProjects();

    @Query(nativeQuery = true,
            value = "SELECT e.first_name || ' ' || e.last_name AS fullName,count(e.employee_id) " +
                    "AS projectCount FROM employee e" +
                    " LEFT JOIN project_employee pe ON (pe.employee_id = e.employee_id) GROUP BY " +
                    "fullName")
    public List<EmployeeChartData> employeeChartData();

    public Employee findByEmail(String email);
}
