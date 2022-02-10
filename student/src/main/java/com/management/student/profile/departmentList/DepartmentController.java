package com.management.student.profile.departmentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    public DepartmentServices departmentServices;

    List<String> departments = new ArrayList<>();

    @GetMapping("/api/department")
    public List<DepartmentEntity> getDepartments(){
        return this.departmentServices.getDepartments();
    }

//    @GetMapping("/api/departments")
//    public String createDepartment(){
//        return "post request";
//    }



}
