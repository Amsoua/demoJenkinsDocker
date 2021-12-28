package com.tuto.docker.jenkins.demo.repository;


import com.tuto.docker.jenkins.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
 Optional<Employee> findByFirstname(String firstname);
}
