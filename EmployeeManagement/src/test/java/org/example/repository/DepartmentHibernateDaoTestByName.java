package org.example.repository;

import org.example.model.Department;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentHibernateDaoTestByName {
    private final DepartmentHibernateDao departmentHibernateDaoTest = new DepartmentHibernateDao();

@Test
    public void getDepartmentsByNameTest() throws SQLException {

        List<Department> departments = departmentHibernateDaoTest.getDepartments();


        assertEquals(0, departments.size());
    }
}
