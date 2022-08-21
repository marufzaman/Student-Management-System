package com.management.student.profile.courseList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseServiceImp  implements  CourseService{


    @Autowired
    private  CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public CourseEntity createCourse(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
        /*if(!courseEntity.getCourseName().isEmpty()){
            return courseRepository.save(courseEntity);
        }else{
            throw  new RuntimeException("Course Name should not be null!");
        }*/

    }

    @Override
    public CourseEntity getCourseById(Long courseId) {


        if(courseRepository.findById(courseId).isPresent()){
            return courseRepository.findById(courseId).get();
        }else{
            throw new RuntimeException("courseId not found!");

        }


        
    }

    @Override
    public CourseEntity updateCourse(CourseEntity courseEntity) throws Exception{

        if(courseRepository.findById(courseEntity.getCourseId()).isPresent()){
            if(!(courseEntity.getCourseName().isEmpty())){
                return courseRepository.save(courseEntity);
            }else{
                throw new Exception("Course Name must not be empty ");
            }
        }else{
            throw new Exception("Id is not found");
        }

        //return  courseRepository.save(courseEntity);


    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
       /* if (courseRepository.findById(courseId).isPresent()){
            courseRepository.deleteById(courseId);
        }else {
            throw new RuntimeException("courseId not found!");
        }*/

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
