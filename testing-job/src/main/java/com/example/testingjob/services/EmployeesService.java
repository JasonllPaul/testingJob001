package com.example.testingjob.services;

import com.example.testingjob.dto.Employee;
import com.example.testingjob.misc.WebClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sonarsource.scanner.api.internal.shaded.okhttp.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeesService implements IEmployeesService {

    private Employee setEmployeeData(JSONObject data) throws JSONException {
        Long iD = Long.parseLong(data.getString("id"));
        String employeeName = data.getString("employee_name");
        double employeeSalary = Double.parseDouble(data.getString("employee_salary"));
        double employeeAnnualSalary = employeeSalary * 12;
        int employeeAge = Integer.parseInt(data.getString("employee_age"));
        String profileImage = data.getString("profile_image");
        Employee employee = new Employee();
        employee.setId(iD);
        employee.setEmployee_name(employeeName);
        employee.setEmployee_salary(employeeSalary);
        employee.setEmployee_anual_salary(employeeAnnualSalary);
        employee.setEmployee_age(employeeAge);
        employee.setProfile_image(profileImage);
        return employee;
    }

    @Override
    public ResponseEntity<?> get(Long id) throws IOException {


        Map<String, Object> responseTemplate = new HashMap<>();
        JSONObject data;
        String jsonString;
        WebClient webClient = new WebClient();
        Response response;
        Employee employee;
        String url = "https://dummy.restapiexample.com/api/v1/employee/".concat(id.toString());
        String method = "GET";


        response = webClient.makeRequest(method, url, null);

        jsonString = response.body().string();


        try {

            JSONObject jObject = new JSONObject(jsonString);
            data = jObject.getJSONObject("data");

            employee = setEmployeeData(data);

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (JSONException e) {

            if (response.code() != 429) {
                responseTemplate.put("message", HttpStatus.NOT_FOUND);
                responseTemplate.put("code", 404);
            } else {
                responseTemplate.put("message", HttpStatus.EXPECTATION_FAILED);
                responseTemplate.put("code", response.code());
            }
            return new ResponseEntity<>(responseTemplate, HttpStatus.OK);
        }
    }

    @Override
    public List<Employee> get() throws IOException, JSONException {


        WebClient webClient = new WebClient();
        Response response;
        Employee employee;
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        String method = "GET";

        response = webClient.makeRequest(method, url, null);
        String jsonString = response.body().string();

        try {
            JSONObject jObject = new JSONObject(jsonString);
            JSONArray jArray = jObject.getJSONArray("data");
            List<Employee> employees = new ArrayList<>();


            for (int i = 0; i < jArray.length(); i++) {
                JSONObject object = jArray.getJSONObject(i);
                employee = setEmployeeData(object);
                employees.add(employee);
            }

            return employees;

        } catch (JSONException e) {
            return new ArrayList<>();
        }
    }


}
