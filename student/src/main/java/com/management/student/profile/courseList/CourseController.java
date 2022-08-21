package com.management.student.profile.courseList;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseController {

    @Autowired
    CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // get all courses
    @GetMapping
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }



    // add new course
    @PostMapping
    public CourseEntity addCourse(@RequestBody CourseEntity courseEntity) {

        return this.courseService.createCourse(courseEntity);
    }


    // update course
    @PutMapping(path = "{courseID}")
    public CourseEntity updateCourse(@PathVariable Long courseID, @RequestBody CourseEntity courseEntity) throws Exception {




        // get course from database by id
        CourseEntity existingCourse = courseService.getCourseById(courseID);
        //existingCourse.setCourseName(courseEntity.getCourseName());

        // New Code
        CourseEntity temp = existingCourse;
        temp.setCourseName(courseEntity.getCourseName());

        return courseService.updateCourse(temp);
        //return "course updated";

    }

    // delete course
    @DeleteMapping(path = "{courseID}")
    public ResponseEntity deleteCourse(@PathVariable Long courseID) {

        // get student from database by courseId



        courseService.deleteCourse(courseID);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping(path = "{courseID}")
    public CourseEntity getCourseById(@PathVariable Long courseID) {

        // get course from database by id
        return courseService.getCourseById(courseID);


    }


}
