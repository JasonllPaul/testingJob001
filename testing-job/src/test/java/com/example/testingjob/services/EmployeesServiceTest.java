package com.example.testingjob.services;

import com.example.testingjob.misc.WebClient;
import org.junit.jupiter.api.Test;
import org.sonarsource.scanner.api.internal.shaded.okhttp.Response;

import java.io.IOException;

class EmployeesServiceTest {

    /**
     * Testing of getting employee by id
     *
     * @throws IOException
     */
    @Test
    void get() throws IOException {
        WebClient webClient = new WebClient();
        Response response;
        String method = "GET";
        String url = "https://dummy.restapiexample.com/api/v1/employee/0";
        response = webClient.makeRequest(method, url, null);
        assert response != null;
    }

    /**
     * Testing of getting all employees
     *
     * @throws IOException
     */
    @Test
    void testGet() throws IOException {
        WebClient webClient = new WebClient();
        Response response;
        String method = "GET";
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        response = webClient.makeRequest(method, url, null);
        assert response != null;
    }
}