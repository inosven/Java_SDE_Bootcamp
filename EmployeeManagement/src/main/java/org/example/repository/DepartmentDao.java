package org.example.repository;

import org.example.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    List<Department> getDepartments() throws SQLException;
}
