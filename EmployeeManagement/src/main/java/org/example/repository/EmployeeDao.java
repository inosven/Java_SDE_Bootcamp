package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface EmployeeDao {
    List<Employee> getEmployees() throws SQLException;
}
