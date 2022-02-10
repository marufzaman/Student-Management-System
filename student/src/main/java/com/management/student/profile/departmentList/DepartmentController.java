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

    List<String> departments = new ArrayList<>();

    @GetMapping("/api/department")
    public List<DepartmentEntity> getDepartments(){
        return (List<DepartmentEntity>) repo.findAll();
    }

    @GetMapping("/api/department/{id}")
    public Optional<DepartmentEntity> getDepartment(@PathVariable Integer id){
        return repo.findById(id);
    }

    @PostMapping ("/api/department")
    public DepartmentEntity createDepartments(@RequestBody DepartmentEntity department){
        repo.save(department);
        return department;
    }

    @DeleteMapping ("/api/department/{id}")
    public ResponseEntity deleteDepartments(@PathVariable Integer id){
        Optional<DepartmentEntity> dept = repo.findById(id);

        if(!dept.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping ("/api/department/{id}")
    public ResponseEntity deleteDepartments(@PathVariable Integer id, @RequestBody DepartmentEntity department){
        Optional<DepartmentEntity> dept = repo.findById(id);

        if(!dept.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repo.save(department);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
