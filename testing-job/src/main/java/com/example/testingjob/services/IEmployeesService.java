package com.example.testingjob.services;

import com.example.testingjob.dto.Employee;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface IEmployeesService {

    ResponseEntity<?> get(Long id) throws IOException, JSONException;
    List<Employee> get() throws IOException, JSONException;
}
