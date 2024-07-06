package com.Practise.DtoPractise.Repository;

import com.Practise.DtoPractise.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findBySal(long sal);
}
