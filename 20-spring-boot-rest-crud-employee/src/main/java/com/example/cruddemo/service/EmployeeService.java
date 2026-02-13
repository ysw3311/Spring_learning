package com.example.cruddemo.service;

import com.example.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee employee);

    void delete(int theId);
}
