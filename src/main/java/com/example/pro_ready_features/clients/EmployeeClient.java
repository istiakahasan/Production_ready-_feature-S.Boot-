package com.example.pro_ready_features.clients;

import com.example.pro_ready_features.dto.EmployeeDTO;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface EmployeeClient {
     List<EmployeeDTO> getAllEmployees() ;

     EmployeeDTO getEmployeeById(Long Id);

     EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
