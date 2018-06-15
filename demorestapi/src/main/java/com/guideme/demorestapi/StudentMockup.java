package com.guideme.demorestapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.sql.PreparedStatement;

public class StudentMockup {
	List<Student> students = new ArrayList<Student>();

	public StudentMockup() {
		Student student1 = new Student("Arun", "Amsi");
		Student student2 = new Student("Chris", "Jorden");
		Student student3 = new Student("Phil", "Coulson");
		students = Arrays.asList(student1, student2, student3);
	}

	public List<Student> getStudents() throws SQLException {
		List<Student> students = new ArrayList<Student>();
		ResultSet result = getConnection().createStatement().executeQuery("select * from student");
		while (result.next()) {
			Student stu = new Student();
			stu.setFirstName(result.getString(2));
			stu.setLastName(result.getString(3));
			students.add(stu);
		}
		return students;
	}

	public Student getStudent(String name) {
		return students.stream().filter(p -> p.getFirstName().equals(name)).findAny().orElse(null);
	}

	public void createStudent(Student student) throws Exception{
		PreparedStatement ps = getConnection().prepareStatement("insert into student (firstname,lastname) values (?,?)");
		ps.setString(1, student.getFirstName());
		ps.setString(2, student.getLastName());
		ps.executeUpdate();
	}

	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

}
