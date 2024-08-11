package org.example.repository;

import org.example.model.Department;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentJBDCDaoTest {
    private DepartmentJDBCDao departmentJDBCDaoTest;
    @Test

    public void getDepartmentTest() throws SQLException {
        DepartmentJDBCDao departmentJDBCDaoTest= new DepartmentJDBCDao();
        List<Department> departments = departmentJDBCDaoTest.getDepartments();
        assertEquals(0, departments.size());

    }
}
