package org.example.repository;

import org.example.model.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentHibernateDaoTest {
    private final DepartmentHibernateDao departmentHibernateDaoTest = new DepartmentHibernateDao();
    Department departmentTest = new Department();
    @Before
    public void setUp() throws Exception {

        departmentHibernateDaoTest.create("Test department", "Test department description","Test location");
    }
    @After
    public void tearDown() throws Exception {
        Department departmentTest = new Department();
        departmentTest = departmentHibernateDaoTest.getDepartmentsByName("Test department");
        departmentHibernateDaoTest.deleteByName(departmentTest.getName());
    }

    @Test
    public void getDepartmentHibernateTest() throws SQLException {

        List<Department> departments = departmentHibernateDaoTest.getDepartments();


        assertEquals(3, departments.size());
    }
//@Test
//    public void deleteDepartmentHibernateTest() throws SQLException {
//    departmentHibernateDaoTest.deleteById(departmentTest.getId());
//    List<Department> departments = departmentHibernateDaoTest.getDepartments();
//    assertEquals(0, departments.size());
//}
}
