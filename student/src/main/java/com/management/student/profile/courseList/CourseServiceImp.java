package com.management.student.profile.courseList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseServiceImp  implements CourseService{



    @Autowired
    private CourseRepository courseRepository;

    List<CourseEntity> courseEntityList;


    @Override
    public List<CourseEntity> getAllCourses() {
        courseEntityList = new ArrayList<>();
        courseEntityList.add(new CourseEntity("English"));
        courseEntityList.add(new CourseEntity("Bangla"));

        return courseEntityList;
    }

    @Override
    public CourseEntity createCourse(CourseEntity courseEntity) {
        return null;
    }

    @Override
    public CourseEntity getCourseById(Long id) {
        return null;
    }

    @Override
    public CourseEntity updateCourse(CourseEntity courseEntity) {
        return null;
    }

    @Override
    public void deleteCourse(Long id) {

    }
}
