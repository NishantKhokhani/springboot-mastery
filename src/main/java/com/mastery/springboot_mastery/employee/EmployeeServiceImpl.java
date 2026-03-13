package com.mastery.springboot_mastery.employee;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import com.mastery.springboot_mastery.employee.dto.EmployeeRequest;
import com.mastery.springboot_mastery.employee.dto.EmployeeResponse;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        log.info("Created new employee with email {}", request.getEmail());

        Employee employee = Employee.builder()
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .department(request.getDepartment())
                            .position(request.getPosition())
                            .salary(request.getSalary())
                            .build();
        Employee saveEmployee = employeeRepository.save(employee);
        log.info("Created new employee with id {}", saveEmployee.getId());

        return mapToResponse(saveEmployee);

    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        log.info("Fetching employee by id: {}", id);

        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("employee not found by id " + id));

        return mapToResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        log.info("Fetching all employees");

        return employeeRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();

    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        log.info("update employee by id {}", id);

        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found by id" + id));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getFirstName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());

        Employee updateEmployee = employeeRepository.save(employee);
        log.info("updated employee successfully with id {}",updateEmployee.getId());

        return mapToResponse(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("deleting employee by id {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("employee not found with id" + id));

        employeeRepository.delete(employee);
        log.info("employee deleted successfully by id {}",id);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return EmployeeResponse.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .email(employee.getEmail())
            .department(employee.getDepartment())
            .position(employee.getPosition())
            .salary(employee.getSalary())
            .createdAt(employee.getCreatedAt())
            .updatedAt(employee.getUpdatedAt())
            .build();
    }
}
