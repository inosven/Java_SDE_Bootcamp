package org.example.service;

import org.example.model.Department;
import org.example.repository.DepartmentHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentHibernateDao departmentHibernateDao;

    public List<Department> getDepartments() throws SQLException {
        return departmentHibernateDao.getDepartments();
    }

    public Department getDepartmentsByName(String departmentName) throws SQLException {
        return departmentHibernateDao.getDepartmentsByName(departmentName);
    }

    public Department update(Department department) throws SQLException {

        return departmentHibernateDao.update(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentHibernateDao.getDepartmentById(id);
    }

    public  void create(Department department) throws SQLException {
        departmentHibernateDao.create(department);
    }
//    public Department getDepartmentById(Long id) {
//    }
}
