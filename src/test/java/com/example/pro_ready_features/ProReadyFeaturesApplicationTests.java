package com.example.pro_ready_features;

import com.example.pro_ready_features.clients.EmployeeClient;
import com.example.pro_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProReadyFeaturesApplicationTests {
	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void getEmployees() {
		List<EmployeeDTO> employeeDTOList=employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}
	@Test
	void getEmployeeById(){
		EmployeeDTO employeeDTO=employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}
	@Test
	void createEmployeeTest(){
		EmployeeDTO employeeDTO=new EmployeeDTO(null,"istiak","istiak21@gmail.com",20,20000.020,LocalDate.of(2020,02,25),true))
		EmployeeDTO employeeDTOCreate=employeeClient.createNewEmployee(employeeDTO);
		System.out.println(employeeDTOCreate);
	}
}
