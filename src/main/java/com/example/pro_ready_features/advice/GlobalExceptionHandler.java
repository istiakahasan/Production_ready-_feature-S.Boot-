package com.example.pro_ready_features.advice;

//import com.example.EmployeeAPI.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse<?>>  handleResourceNotFound(ResourceNotFoundException exception){
//        ApiError apiError= ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
//    return buildApiResponseEntity(apiError);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>>  handleInternalserverError(Exception exception){
        ApiError apiError= ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return buildApiResponseEntity(apiError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputvalidationError(MethodArgumentNotValidException exception){
       List<String> errors= exception.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toList());
       ApiError apiError= ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Input is not valid").subErrors(errors).build();
        return buildApiResponseEntity(apiError);
    }
    public ResponseEntity<ApiResponse<?>> buildApiResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }

}
