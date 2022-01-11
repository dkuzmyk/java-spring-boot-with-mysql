package com.dkyzmy.test.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class employeeService {

    private final employeeRepository empRep;

    @Autowired
    public employeeService(employeeRepository empRep) {
        this.empRep = empRep;
    }

    public List<employee> getEmployee(){
        return empRep.findAll();
    }

    public void addEmployee(employee e) {
        // check if email exists, if it doesn't we add the user
        Optional<employee> employeeEmail = empRep.findemployeeByEmail(e.getEmail());

        // if email exists, throw exception
        if(employeeEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        // once we pass here, we save the user as it doesn't exist yet
        empRep.save(e);

        System.out.println(e);
    }

    public void deleteEmployee(Long id) {
        if(!empRep.existsById(id)){
            throw new IllegalStateException("Employee with id: "+id+" does not exist.");
        }
        empRep.deleteById(id);
    }

    @Transactional // manage state -> doesn't need query
    public void updateEmployee(Long id, String name, String email) {
        // check if exists
        employee e = empRep.findById(id).orElseThrow( () -> new IllegalStateException("Employee with id: "+id+ " does not exist."));

        // if name is not null -> name was given, name is not empty string and name is not equal to what we already have
        if(name != null && name.length() > 0 && !Objects.equals(e.getName(), name)){
            // we want to change the name
            e.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(e.getEmail(), email)){
            // check if email was not taken, since email is unique
            Optional<employee> tempEmployee = empRep.findemployeeByEmail(email);
            if(tempEmployee.isPresent()){
                throw new IllegalStateException("This email already exists: "+email);
            }

            e.setEmail(email);
        }

    }
}
