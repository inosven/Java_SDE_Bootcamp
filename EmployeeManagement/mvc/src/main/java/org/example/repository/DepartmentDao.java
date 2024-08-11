package org.example.repository;

import org.example.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    List<Department> getDepartments() throws SQLException;


    //    void create(String name, String description, String location) throws SQLException;
    void create(Department department) throws SQLException;

    Department update(Department department);

    Department getDepartmentsByName(String departmentName) throws SQLException;
//    Department update(String name, String description, String location) throws SQLException;

    void delete(Department department) throws SQLException;

    Department getDepartmentEagerBy(Long id) throws SQLException;

    Department getDepartmentById(Long id);
}
