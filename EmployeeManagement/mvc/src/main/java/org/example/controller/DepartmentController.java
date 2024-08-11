package org.example.controller;

import org.example.model.Department;
import org.example.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Department> getDepartments() throws SQLException {
        List<Department> departments = departmentService.getDepartments();
        return departments;
    }

    @RequestMapping(value = "/{departmentName}", method = RequestMethod.GET, params = {"departmentName"})
    public Department getDepartmentbyName(@PathVariable(name="departmentName") String name) throws SQLException {

        return departmentService.getDepartmentsByName(name);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"departmentName"})
    public Department updateDepartmentName(@PathVariable("id") Long id, @RequestParam("departmentName") String name) throws SQLException {
        logger.info("pass in variable id: {} and name: {}", id.toString(), name);
        Department d = departmentService.getDepartmentById(id);
        d.setName(name);
        d = departmentService.update(d);
        return d;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void addDepartment(@RequestBody Department department) throws SQLException {
        logger.info("add new record in department: {}", department);
        departmentService.create(department);
    }
}
