package com.management.student.profile.studentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	public ResponseEntity<StudentProfile> getStudent(@PathVariable("studentID") Long studentID) {
		try{
			return ResponseEntity.ok(studentService.getStudent(studentID).orElseThrow(RuntimeException::new));
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<StudentProfile> addNewStudentProfile(@Valid @RequestBody StudentProfile studentProfile){
		try{
			return ResponseEntity.ok(studentService.addNewStudent(studentProfile));
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "{studentID}")
	public ResponseEntity<Object> deleteStudentProfile(@PathVariable("studentID") Long studentID) throws Exception {
		try{
			return ResponseEntity.ok(studentService.deleteStudentProfile(studentID));
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping(path = "{studentID}")
	public  ResponseEntity<StudentProfile> editStudentProfile(
			@Valid
			@PathVariable("studentID") Long studentID,
			@RequestBody(required = false) StudentProfile newInfo
	) {
		try{
			return ResponseEntity.ok(studentService.editStudentProfile(studentID, newInfo));
		}catch (Exception e){
			return ResponseEntity.notFound().build();
		}
	}
}