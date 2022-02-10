package com.management.student.profile.courseList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@EnableAutoConfiguration
@Repository
public interface CourseRepository  extends JpaRepository<CourseEntity,Long> {

}