package org.example.repository;

import org.example.model.Department;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.ApplicationBootstrap;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class DepartmentHibernateDaoTest {
    @Autowired
    private SessionFactory mockSessionFactory;
    @Mock
    private Session mockSession;
    @Mock
    private Query mockQuery;
    @Autowired
    private DepartmentHibernateDao departmentDao;
    @Test
    public void getDepartmentsTest_happyPath() throws SQLException {
        Department department = new Department(1, "Zhang3", "Test description", "Test location");
        try (MockedStatic<HibernateUtil> mockedStatic = mockStatic(HibernateUtil.class)) {
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);
            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(List.of(department));
            doNothing().when(mockSession).close();

//            DepartmentDao departmentDao = new DepartmentHibernateDao();
            List<Department> actualResuls = departmentDao.getDepartments();
            assertEquals(List.of(department), actualResuls);
        }


    }
    @Test
    public void getDepartmentsTest_closeSessionException_getHibernateException() throws SQLException {
        Department department = new Department(1, "Zhang3", "Test description", "Test location");
        try (MockedStatic<HibernateUtil> mockedStatic = mockStatic(HibernateUtil.class)) {
            mockedStatic.when(HibernateUtil::getSessionFactory).thenReturn(mockSessionFactory);
            when(mockSessionFactory.openSession()).thenReturn(mockSession);
            when(mockSession.createQuery(any(String.class))).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(List.of(department));
            doThrow(HibernateException.class).when(mockSession).close();

//            DepartmentDao departmentDao = new DepartmentHibernateDao();
            assertThrows(HibernateException.class, () -> departmentDao.getDepartments());
        }
    }


    }
