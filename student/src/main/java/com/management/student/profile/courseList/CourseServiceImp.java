package com.management.student.profile.courseList;

import com.management.student.profile.studentList.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImp  implements  CourseService{


    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseEntity createCourse(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    @Override
    public CourseEntity getCourseById(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public CourseEntity updateCourse(CourseEntity courseEntity) {

        return courseRepository.save(courseEntity);
    }

    @Override
    public void deleteCourse(Long courseId) {

        courseRepository.deleteById(courseId);
    }

/*
    @Transactional
    public void editStudentProfile(Long studentID, String name, String gender) {
        StudentProfile studentProfile =  studentProfileRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException(
                        "Record for the student doesn't exists. Probably removed by a user."
                ));

        if (name != null && name.length() > 0 &&
                !Objects.equals(studentProfile.getName(), name)){
            studentProfile.setName(name);
        }

        if (gender != null && gender.length() > 0 &&
                !Objects.equals(studentProfile.getGender(), gender)){
            studentProfile.setGender(gender);
        }
    }*/

}
