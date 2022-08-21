package com.management.student.profile.studentList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentProfileRepository studentProfileRepository;

	public StudentService(StudentProfileRepository studentProfileRepository) {
		this.studentProfileRepository = studentProfileRepository;
	}

	public List<StudentProfile> getStudentProfiles(){
		return studentProfileRepository.findAll();
	}

	public Optional<StudentProfile> getStudent(Long studentID) throws Exception{
		if(!(studentProfileRepository.existsById(studentID))){
			throw new Exception(
					"Record doesn't exists!"
			);
		}
		return studentProfileRepository.findById(studentID);
	}

	public StudentProfile addNewStudent(StudentProfile studentProfile){
		return studentProfileRepository.save(studentProfile);
	}

	public Long deleteStudentProfile(Long studentID) throws Exception{
		if(!(studentProfileRepository.existsById(studentID))){
			throw new Exception(
					"Record doesn't exists."
			);
		}
		studentProfileRepository.deleteById(studentID);
		return studentID;
	}

	@Transactional
	public StudentProfile editStudentProfile(Long studentID, StudentProfile newInfo) throws Exception{
		StudentProfile studentProfile
				= studentProfileRepository.findById(studentID)
				.orElseThrow(() -> new Exception(
						"Record doesn't exists."
				));

		if (!Objects.equals(studentProfile.getName(), newInfo.getName())){
			studentProfile.setName(newInfo.getName());
		}

		if (!Objects.equals(studentProfile.getGender(), newInfo.getGender())){
			studentProfile.setGender(newInfo.getGender());
		}
		studentProfileRepository.save(studentProfile);

		return studentProfile;
	}


}