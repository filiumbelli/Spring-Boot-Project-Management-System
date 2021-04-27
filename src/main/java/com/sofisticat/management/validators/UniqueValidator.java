package com.sofisticat.management.validators;

import com.sofisticat.management.dao.EmployeeRepository;
import com.sofisticat.management.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Validation ----------");
        Employee emp = employeeRepository.findByEmail(value);
        if (emp != null) {
            return false;
        } else
            return true;
    }
}
