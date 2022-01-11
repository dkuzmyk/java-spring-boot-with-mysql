package com.dkyzmy.test.demo.employee;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class employee {

    @Id
    @SequenceGenerator(
            name = "Employee_sequence",
            sequenceName = "Employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Employee_sequence"
    )
    @Column(    // specify options for column
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    private String lastName;
    private LocalDate dob;

    @Column(
            name = "email",
            unique = true
    )
    private String email;
    @Transient      // calculated for us
    private int age;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public employee(Long id, String name, String lastName, LocalDate dob, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    public employee(String name, String lastName, LocalDate dob, String email) {
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    public employee() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Employee [ID: " + id + ", name: " + name + ", last name: " + lastName + ", DOB: " + dob + ", email: " + email +"]";
    }
}
