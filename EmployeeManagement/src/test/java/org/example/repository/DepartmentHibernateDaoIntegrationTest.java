package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentHibernateDaoIntegrationTest {
    private final DepartmentHibernateDao departmentHibernateDaoTest = new DepartmentHibernateDao();
    Department departmentTest = new Department();
    Employee employeeTest = new Employee();
    Employee employeeTest2 = new Employee();

    @Before
    public void setUp() throws Exception {
        departmentTest.setLocation("Test_location");
        departmentTest.setDescription("Test_Description");
        departmentTest.setName("Test_name");
        employeeTest.setDepartment(departmentTest);
        employeeTest.setAddress("Baker Street");
        employeeTest.setName("Jelly Baker");
        employeeTest2.setDepartment(departmentTest);
        employeeTest2.setAddress("9806 Laurel St, Fairfax");
        employeeTest2.setName("Ino");


        departmentHibernateDaoTest.create(departmentTest);

    }

    @After
    public void tearDown() throws Exception {
        departmentHibernateDaoTest.delete(departmentTest);
    }

    @Test
    public void getDepartmentHibernateTest() throws SQLException {

        List<Department> departments = departmentHibernateDaoTest.getDepartments();


        assertEquals(15, departments.size());
    }

    //@Test
//    public void deleteDepartmentHibernateTest() throws SQLException {
//    departmentHibernateDaoTest.deleteById(departmentTest.getId());
//    List<Department> departments = departmentHibernateDaoTest.getDepartments();
//    assertEquals(0, departments.size());
//}
    @Test
    public void getDepartmentEagerByTest() {
        Department department = departmentHibernateDaoTest.getDepartmentEagerBy(departmentTest.getId());
        assertNotNull(department);
        assertEquals(department.getName(), departmentTest.getName());
        assertTrue(department.getEmployees().size() > 0);
    }


}
