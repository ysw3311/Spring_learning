package com.example.cruddemo.rest;


import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper) {
        this.employeeService = theEmployeeService;
        jsonMapper = theJsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeesId}")
    public Employee getEmployee(@PathVariable int employeesId) {
        Employee theEmployee = employeeService.findById(employeesId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee is not found" + employeesId);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;

    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> patchPayload) {
        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null) {
            throw new RuntimeException("Employee is not found" + employeeId);
        }
        // 수정 json에 id가 포함된다면 해당 문구 띄우기
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not is allowed in request body -" + employeeId);

        }
        Employee patchedEmployee = jsonMapper.updateValue(tempEmployee, patchPayload);
        Employee dbEmployee = employeeService.save(patchedEmployee);
        return dbEmployee;

    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null) {
            throw new RuntimeException("Employee is not found" + employeeId);
        }
        employeeService.delete(employeeId);
        
        return "Deleted employee id -" + employeeId;
    }
}
