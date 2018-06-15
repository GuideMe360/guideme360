package com.guideme.demorestapi;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// studentService/student

@Path("studentServiceAPI")
public class StudentResource {

	StudentMockup mockup = new StudentMockup();

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public List<Student> getStudent() throws SQLException {
		return mockup.getStudents();
	}

	@POST
	@Path("studentAPI")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Student createNewStudent(Student s) throws Exception{
		mockup.createStudent(s);
		return s;
	}

	@GET
	@Path("studentAPI/{name}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Student getStudent(@PathParam("name") String name) {
		return mockup.getStudent(name);
	}

}
