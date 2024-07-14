package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> getDepartmentById(Long id);

    Department saveDepartment(Department department);

    void deleteDepartmentById(Long id);               
}
