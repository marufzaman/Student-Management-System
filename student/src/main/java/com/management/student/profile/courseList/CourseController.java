package com.management.student.profile.courseList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;



    // get all courses
    @GetMapping(path="/api/courses",produces={"application/json"})
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }



    // add new course
    @PostMapping("/api/department{id}/courses/add")
    public String addCourse(@PathVariable Long id,Model model) {

        CourseEntity courseEntity = new CourseEntity();
        model.addAttribute("course",courseEntity);
        /*
         * ModelAndView mv = new ModelAndView(); mv.addObject("students.jsp");
         * model.addAttribute("message","Hello Java Geeks");
         */
        return "course added";
    }


    // update course
    @PostMapping("/department{id}/courses{id}/update")
    public String updateStudent(@PathVariable Long id,@ModelAttribute("course")CourseEntity courseEntity,Model model) {

        // get course from database by id
        CourseEntity existingCourse = courseService.getCourseById(id);
        existingCourse.setCourseId(id);
        existingCourse.setCourseName(courseEntity.getCourseName());


        courseService.updateCourse(existingCourse);
        return "course updated";

    }


    // delete course
    @PostMapping("/courses/{id}")
    public String deleteStudent(@PathVariable Long id) {

        // get student from database by id


        courseService.deleteCourse(id);
        return "course deleted";

    }


/*

    //create & submit new student's data
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student")Student student) {
        studentService.saveStudent(student);

        return "redirect:/students";

    }


    // fetch student's data on update form
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {

        //Student student = studentService.getStudentById(id);
        //studentService.saveStudent(student);

        model.addAttribute("student",studentService.getStudentById(id));
        return "edit_student";
    }


    //submit student's updated data
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,@ModelAttribute("student")Student student,Model model) {

        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setStudentDate(student.getStudentDate());
        existingStudent.setStudentGender(student.getStudentGender());
        existingStudent.setStudentNote(student.getStudentNote());

        studentService.updateStudent(existingStudent);
        return "redirect:/students";

    }



    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {

        // get student from database by id


        studentService.deleteStudent(id);
        return "redirect:/students";

    }*/

}
