package in.sp.infy;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import in.sp.infy.entity.Students;
import in.sp.infy.service.StudentsService;
import in.sp.infy.service.StudentsServiceImpl;

@SpringBootApplication
public class SpbDataJpaApplication {

	static StudentsService studentService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpbDataJpaApplication.class, args);
		studentService = context.getBean(StudentsServiceImpl.class);
		if (studentService == null) {
			System.out.println("Bsdk null ahe");
		} else {
			System.out.println("not null " + studentService);
		}
		Scanner sc = new Scanner(System.in);

		boolean status = true;
		while (status) {
			System.out.println("Select Operation");
			System.out.println(
					"1: Add Student \n2: Show All Student List \n3: Delete Student \n4: Update Student Marks\n5: Search Student By Id \n0: Exit");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter the Student Name");
				String name = sc.next();
				System.out.println("Enter the Student Roll No");
				int rollNo = sc.nextInt();
				System.out.println("Enter the Marks");
				float marks = sc.nextFloat();
				addStudents(name, rollNo, marks);
				break;

			case 2:

				showAllStudentList();
				break;

			case 3:
				System.out.println("Enter the Student Id");
				long idForDelete = sc.nextLong();
				deleteStudent(idForDelete);
				
				break;
			case 4:
				System.out.println("Enter the Student Id");
				long idForUpdate = sc.nextLong();
				System.out.println("Enter the Student Marks");
				float marksForUpdate = sc.nextFloat();
				updateStudent(idForUpdate, marksForUpdate);
				break;

			case 5:
				System.out.println("Enter the Student Id");
				long id = sc.nextLong();
				searchById(id);
				break;
				
			case 0:
				status = false;
				break;
			default:
				break;
			}
		}

	}

	private static void searchById(long id) {
		Students stud = studentService.getStudent(id);
		if (stud != null) {
			System.out.println("-------------------------------------------");
			System.out.println(stud.getId());
			System.out.println(stud.getName());
			System.out.println(stud.getRollNo());
			System.out.println(stud.getMarks());
		} else {
			System.out.println("Student Not Found");
		}
	}

	private static void updateStudent(long id, float marks) {
		boolean status = studentService.updateStudent(id, marks);
		System.out.println(
				(status) ? "Marks Updated Successfully!!" : "Marks Unable to Update... Please try Again later");

	}

	private static void deleteStudent(long idForDelete) {
		if(studentService.deleteStudentById(idForDelete)) {
			System.out.println("Student Record is deleted ");
		}else {
			System.out.println("Student Record is not deleted");
		}

	}

	private static void showAllStudentList() {
//		System.out.println("-------------------------------------------");
		List<Students> studentsList = studentService.getAllStudents();
		if (studentsList != null) {
			for (Students student : studentsList) {
				System.out.println("-------------------------------------------");
				System.out.println(student.getId());
				System.out.println(student.getName());
				System.out.println(student.getRollNo());
				System.out.println(student.getMarks());
			}
		} else {
			System.out.println("No data found");
		}

	}

	private static void addStudents(String name, int rollNo, float marks) {
		Students stud = new Students();
		stud.setName(name);
		stud.setRollNo(rollNo);
		stud.setMarks(marks);

		boolean isStudentSave = studentService.addStudent(stud);
		if (isStudentSave) {
			System.out.println("Student Save Successfully");
		} else {
			System.out.println("Student Save Ops Failed");
		}

	}

}
