package com.mastery.springboot_mastery.employee;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.mastery.springboot_mastery.employee.dto.EmployeeRequest;
import com.mastery.springboot_mastery.employee.dto.EmployeeResponse;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
        @RequestBody EmployeeRequest request) {
        log.info("Rest request for create employee");
        EmployeeResponse response = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id) {
        log.info("REST request get employee by id {}",id);
        EmployeeResponse response = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        log.info("REST request to get all employees");
        List<EmployeeResponse> response = employeeService.getAllEmployee();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
        @PathVariable Long id,
        @RequestBody EmployeeRequest request) {

        log.info("REST request update employee with id {}",id);
        EmployeeResponse response = employeeService.updateEmployee(id,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
        @PathVariable Long id) {
        log.info("REST request delete employee with id {}",id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
