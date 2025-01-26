package in.sp.infy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import in.sp.infy.entity.Students;
import in.sp.infy.repository.StudentsRepository;


@Service
public class StudentsServiceImpl implements StudentsService {
	
	@Autowired
	private StudentsRepository studentsRepository;

	@Override
	public boolean addStudent(Students student) {
		boolean status=false;
		try {
			studentsRepository.save(student);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Students> getAllStudents(){		
		return studentsRepository.findAll();
	}

	
	@Override
	public Students getStudent(long id) {
		Optional<Students> student=studentsRepository.findById(id);
		if(student.isPresent()) {
			return student.get();//it return the Students Object
		}
		return null ;
	}
	@Override
	public boolean updateStudent(long id ,float marks) {
		Students stud=getStudent(id);
		if(stud != null) {
			try {
				stud.setMarks(marks);
				studentsRepository.save(stud);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;
	}

	@Override
	public boolean deleteStudentById(long id) {
		
		try {
			studentsRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
