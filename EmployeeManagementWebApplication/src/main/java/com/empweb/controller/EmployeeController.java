package com.empweb.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empweb.entity.Employee;
import com.empweb.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping
    public List<Employee> getAllEmployees() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println("Current User "+currentPrincipalName);
		return employeeService.getAllEmployees();
    }
    
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(createdEmployee);
    }   

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.ok("Deleted employee id - " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{firstName}")
    public List<Employee> getEmployeesByFirstName(@PathVariable String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @GetMapping("/sort")
    public List<Employee> getAllEmployeesSorted(@RequestParam String order) {
        return employeeService.getAllEmployeesSorted(order);
    }
}
