package com.employee.management.service;

import com.employee.management.model.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee saveEmployee(Employee employee);

    Employee getEmployee(Long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getAllEmployees();

    Employee findByEmail(String email);
}
