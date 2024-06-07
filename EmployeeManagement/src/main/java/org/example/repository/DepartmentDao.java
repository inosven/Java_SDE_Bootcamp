package org.example.repository;

import org.example.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    List<Department> getDepartments() throws SQLException;

    void create(String name, String description, String location) throws SQLException;

    Department getDepartmentsByName(String departmentName) throws SQLException;
    Department create(Long id, String name, String description, String location) throws SQLException;
    Department update(Long id, String name, String description, String location) throws SQLException;
    void deleteByName(String name) throws SQLException;
}
