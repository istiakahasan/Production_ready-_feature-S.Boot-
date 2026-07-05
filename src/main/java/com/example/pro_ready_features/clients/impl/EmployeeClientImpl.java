package com.example.pro_ready_features.clients.impl;

import com.example.pro_ready_features.advice.ApiResponse;
import com.example.pro_ready_features.clients.EmployeeClient;
import com.example.pro_ready_features.dto.EmployeeDTO;
import com.example.pro_ready_features.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    Logger log=LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.trace("trying to retrieve all employees in getAllEmployees");

        try {
            ResponseEntity<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve().onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })


                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieve employees list in getAllEmployees",employeeDTOList.getBody());


            return  employeeDTOList.getBody();

        } catch (Exception e) {
            log.error("Exception occured in getAllEmployees",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get Employee By Id in getEmployeeById with id: ",employeeId);
        try{
            ApiResponse<EmployeeDTO> employeeResponse=restClient.get().uri("empoyees/{employeeId}",employeeId).retrieve().body(new ParameterizedTypeReference<>() {
            });
            return employeeResponse.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create Employee information ",employeeDTO);
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse=restClient.post().uri("employees")
                    .body(employeeDTO).retrieve().onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.debug("4xxClient error occured during createNewEmployee");
                        throw new ResourceNotFoundException("could not create the employee");
                    }).toEntity(new ParameterizedTypeReference< >() {
                    });
            log.trace("Successfully create a new Employee",employeeDTOApiResponse);
            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e){
            log.error("Exception occured in createNewEmployee");
            throw new RuntimeException(e);
        }
    }


}
