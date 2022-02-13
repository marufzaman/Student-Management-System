package com.management.student.profile.studentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentProfileController {

	private final StudentService studentService;

	@Autowired
	public StudentProfileController(StudentService studentService){
		this.studentService = studentService;
	}

	@GetMapping
	public List<StudentProfile> getStudentProfiles(){
		return studentService.getStudentProfiles();
	}

	@GetMapping(path = "{studentID}")
	public Optional<StudentProfile> getStudent(@PathVariable("studentID") Long studentID){
		return studentService.getStudent(studentID);
	}

	@PostMapping
	public void addNewStudentProfile(@RequestBody StudentProfile studentProfile){
		studentService.addNewStudent(studentProfile);
	}

	@DeleteMapping(path = "{studentID}")
	public void deleteStudentProfile(@PathVariable("studentID") Long studentID){
		studentService.deleteStudentProfile(studentID);
	}

	@PutMapping(path = "{studentID}")
	public void editStudentProfile(
			@PathVariable("studentID") Long studentID,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String gender
	){
		studentService.editStudentProfile(studentID, name, gender);
	}
}