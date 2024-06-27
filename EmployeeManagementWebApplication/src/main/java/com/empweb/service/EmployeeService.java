package com.empweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.empweb.entity.Employee;
import com.empweb.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> updateEmployee(Long id, Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            return employeeRepository.save(employee);
        });
    }

    public boolean deleteEmployee(Long id) {
        return employeeRepository.findById(id).map(employee -> {
            employeeRepository.delete(employee);
            return true;
        }).orElse(false);
    }

    public List<Employee> getEmployeesByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> getAllEmployeesSorted(String order) {
        if ("desc".equalsIgnoreCase(order)) {
            return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
        } else {
            return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        }
    }
}
