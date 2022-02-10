package com.management.student.profile.departmentList;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServices {

    List<DepartmentEntity> departments;

    public DepartmentServices() {
        departments = new ArrayList<>();
        departments.add(new DepartmentEntity(1, "CSE"));

    }


    public List<DepartmentEntity> getDepartments() {
        return departments;
    }


//    public List<DepartmentEntity>  getDepartment(Long id) {
//
//        DepartmentEntity department = departments.forEach(dept -> {
//            if(dept.id == id){
//                return dept;
//            }
//        });
//        return (List<DepartmentEntity>) department;
//    }

}
