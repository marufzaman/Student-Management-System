package com.management.student.profile.departmentList;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServices {

    public final DepartmentRepository departmentRepository;

    public DepartmentServices(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentEntity> getDepartments(){
        List<DepartmentEntity> departments = departmentRepository.findAll();
        return departments;
    }

    public DepartmentEntity getDepartmentById(Integer id){
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
        if(!department.isPresent()){
            throw new IllegalStateException("Department Not Found");
        }
        DepartmentEntity dept = department.get();
        return dept;
    }

    public DepartmentEntity createDepartment(DepartmentEntity department){
        departmentRepository.save(department);
        return department;
    }

    public DepartmentEntity updateDepartment(Integer id, DepartmentEntity department) throws Exception{
        Optional<DepartmentEntity> previousDepartment = departmentRepository.findById(id);

        if(!previousDepartment.isPresent()){
            throw new Exception("Department not found");
        }

        DepartmentEntity updatedDepartment = previousDepartment.get();
        updatedDepartment.setId(department.getId());
        updatedDepartment.setName(department.getName());

        departmentRepository.save(updatedDepartment);

        return updatedDepartment;
    }

    public ResponseEntity deleteDepartment(Integer id) throws Exception {
        Optional<DepartmentEntity> department = departmentRepository.findById(id);

        if(!department.isPresent()){
            throw new Exception("Department not found");
        }

        departmentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
