package com.management.student.profile.courseList;

import java.util.List;

public interface CourseService {


    // show all courses
    List<CourseEntity> getAllCourses();


    // add new Course
    CourseEntity createCourse(CourseEntity courseEntity);


    // get course
    CourseEntity getCourseById(Long id);

    // update course
    CourseEntity updateCourse(CourseEntity courseEntity);

    // delete course
    void deleteCourse(Long id);
}
