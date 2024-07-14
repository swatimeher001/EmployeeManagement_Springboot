package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.service.DepartmentService;     
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.*; 

import java.util.List;           
import java.util.Optional;   

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired    
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping   
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments/list";         // Thymeleaf template name
    }

    @GetMapping("/{id}")
    public String getDepartmentById(@PathVariable Long id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        department.ifPresent(d -> model.addAttribute("department", d));
        return "departments/detail"; // Thymeleaf template name
    }

    @GetMapping("/new")          
    public String createDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/form"; // Thymeleaf template name
    }

    @PostMapping("/save")         
    public String saveDepartment(@ModelAttribute("department") Department department) {
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")            
    public String editDepartmentForm(@PathVariable Long id, Model model) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        department.ifPresent(d -> model.addAttribute("department", d));
        return "departments/form"; // Thymeleaf template name
    }

    @GetMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
