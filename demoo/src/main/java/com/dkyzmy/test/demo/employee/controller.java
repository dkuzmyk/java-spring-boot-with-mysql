package com.dkyzmy.test.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this controls the functions through http
@RestController
@RequestMapping(path="api/v1/employee")
public class controller {

    private final employeeService s;

    @Autowired
    controller(employeeService e){
        this.s = e;
    }

    // retrieve
    @GetMapping
    public List<employee> getEmployee(){
        return s.getEmployee();
    }

    // adding
    @PostMapping
    public void registerEmployee(@RequestBody employee e){
        s.addEmployee(e);
    }

    // removing
    // path takes id such as http://localhost:8080/api/v1/employee/3 -> id=3
    @DeleteMapping(path="{employeeId}")
    public void removeEmployee(@PathVariable("employeeId") Long id){
        s.deleteEmployee(id);
    }

    // editing
    @PutMapping(path="{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        s.updateEmployee(id, name, email);
    }
}
