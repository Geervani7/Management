package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
}
