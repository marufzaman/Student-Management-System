package com.management.student.profile.departmentList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
}
