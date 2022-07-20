package com.example.testingjob;

import com.example.testingjob.dto.Employee;
import com.example.testingjob.services.IEmployeesService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@SpringBootApplication

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestingJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingJobApplication.class, args);
    }

    @Autowired
    private IEmployeesService employeesService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello %s!", name);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) throws JSONException, IOException {
        return employeesService.get(id);
    }


    @GetMapping("/employees")
    public List<Employee> get() throws IOException, JSONException {
        return employeesService.get();
    }

}

