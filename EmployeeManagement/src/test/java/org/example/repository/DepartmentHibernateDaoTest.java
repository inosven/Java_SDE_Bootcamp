package org.example.repository;

import org.example.model.Department;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DepartmentHibernateDaoTest {
    @Mock
    private SessionFactory mockSessionFactory;
    @Mock
    private Session mockSession;
    @Mock
    private Query mockQuery;
    DepartmentDao departmentDao = new DepartmentHibernateDao();
    @Test
    public void getDepartmentsTest() throws SQLException {
        Department department = new Department(1, "Zhang3", "Test description", "Test location");
        try (MockedStatic<HibernateUtil> mockedStatic = mockStatic(HibernateUtil.class)) {
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);
            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(List.of(department));
            doNothing().when(mockSession).close();

            List<Department> actualResuls = departmentDao.getDepartments();
            assertEquals(department, actualResuls);
        }
    }
}
