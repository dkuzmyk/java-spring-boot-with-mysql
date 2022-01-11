package com.dkyzmy.test.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface employeeRepository extends JpaRepository<employee, Long> {

    // find user by email -> Select * from spring_test.employee where email = x

    @Query("SELECT s FROM employee s WHERE s.email = ?1")
    Optional<employee> findemployeeByEmail(String email);
}
