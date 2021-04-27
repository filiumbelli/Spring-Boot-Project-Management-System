package com.sofisticat.management.services;

import com.sofisticat.management.dao.EmployeeRepository;
import com.sofisticat.management.dto.EmployeeChartData;
import com.sofisticat.management.dto.EmployeeDto;
import com.sofisticat.management.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<EmployeeDto> getProjectStatus() {
        return employeeRepository.employeeProjects();
    }

    public Employee getById(long id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeChartData> getChartData() {
        return employeeRepository.employeeChartData();
    }


}
