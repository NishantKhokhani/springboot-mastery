package com.mastery.springboot_mastery.employee;

import java.util.List;

import com.mastery.springboot_mastery.employee.dto.EmployeeRequest;
import com.mastery.springboot_mastery.employee.dto.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);

    List<EmployeeResponse> getAllEmployee();

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    void deleteEmployee(Long id);

}
