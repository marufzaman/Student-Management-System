package com.management.student.profile.studentList;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

	public void deleteStudentProfile(Long studentID) throws Exception{
		if(!(studentProfileRepository.existsById(studentID))){
			throw new Exception(
					"Record doesn't exists."
			);
		}
		studentProfileRepository.deleteById(studentID);
	}

	@Transactional
	public StudentProfile editStudentProfile(Long studentID, String name, String gender) throws Exception{
		StudentProfile studentProfile
				= studentProfileRepository.findById(studentID)
				.orElseThrow(() -> new Exception(
						"Record doesn't exists."
				));

		if (!Objects.equals(studentProfile.getName(), name)){
			studentProfile.setName(name);
		}

		if (!Objects.equals(studentProfile.getGender(), gender)){
			studentProfile.setGender(gender);
		}
		studentProfileRepository.save(studentProfile);

		return studentProfile;
	}


}