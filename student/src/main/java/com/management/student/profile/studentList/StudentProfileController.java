package com.management.student.profile.studentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
	public ResponseEntity<StudentProfile> getStudent(@PathVariable("studentID") Long studentID) throws Exception {
		try{
			return ResponseEntity.ok(studentService.getStudent(studentID).orElseThrow(RuntimeException::new));
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public StudentProfile addNewStudentProfile(@Valid @RequestBody StudentProfile studentProfile){
		return studentService.addNewStudent(studentProfile);
	}

	@DeleteMapping(path = "{studentID}")
	public void deleteStudentProfile(@PathVariable("studentID") Long studentID) throws Exception {
		studentService.deleteStudentProfile(studentID);
	}

	@PutMapping(path = "{studentID}")
	public StudentProfile editStudentProfile(
			@Valid
			@PathVariable("studentID") Long studentID,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String gender
	) throws Exception {
		return studentService.editStudentProfile(studentID, name, gender);
	}
}