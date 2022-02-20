package com.management.student.profile.departmentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    @Autowired
    public DepartmentRepository repo;
    public final DepartmentServices departmentServices;

    List<String> departments = new ArrayList<>();

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @GetMapping("/api/department")
    public List<DepartmentEntity> getDepartments(){
//        return (List<DepartmentEntity>) repo.findAll();
        return departmentServices.getDepartments();
    }

    @GetMapping("/api/department/{id}")
    public DepartmentEntity getDepartment(@PathVariable Integer id) throws Exception {

//        Optional<DepartmentEntity> department = repo.findById(id);
//
//        if(department.isPresent()==false){
//             throw new IllegalStateException("Department Not Found");
//        }
//        DepartmentEntity dept = department.get();
//
//        return dept;

        return departmentServices.getDepartmentById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping ("/api/department")
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity department){
//        repo.save(department);
        return departmentServices.createDepartment(department);
    }

    @DeleteMapping ("/api/department/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Integer id) throws Exception {
//        Optional<DepartmentEntity> department = repo.findById(id);
//
//        if(department.isPresent()==false){
//            throw new Exception("Department not found");
//        }
//
//        repo.deleteById(id);
        return departmentServices.deleteDepartment(id);
    }

    @PutMapping ("/api/department/{id}")
    public DepartmentEntity updateDepartment(@PathVariable Integer id, @RequestBody DepartmentEntity department) throws Exception {
//        Optional<DepartmentEntity> previousDepartment = repo.findById(id);
//
//        if(previousDepartment.isPresent()==false){
//            throw new Exception("Department not found");
//        }
//
//        DepartmentEntity updatedDepartment = previousDepartment.get();
//        updatedDepartment.setId(department.getId());
//        updatedDepartment.setName(department.getName());
//
//        repo.save(updatedDepartment);
//
        return departmentServices.updateDepartment(id, department);
    }


}
