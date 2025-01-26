package in.sp.infy.service;

import java.util.List;



import in.sp.infy.entity.Students;

public interface StudentsService {
	
	public boolean addStudent(Students student);
	public List<Students> getAllStudents();
	public Students getStudent(long id);
	public boolean updateStudent(long id,float marks);
	public boolean deleteStudentById(long id);
	

}
