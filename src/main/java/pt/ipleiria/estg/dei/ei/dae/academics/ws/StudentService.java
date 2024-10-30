package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.List;

@Path("student")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class StudentService {

    @EJB
    private StudentBean studentBean;

    @GET
    @Path("/")
    public List<StudentDTO> getAllStudents() {
        return StudentDTO.from(studentBean.findAll());
    }

    @GET
    @Path("/{username}")
    public Response getStudent(@PathParam("username") String username) throws MyEntityNotFoundException {
        var student = studentBean.findWithSubjects(username);
        var studentDTO = StudentDTO.from(student);
        studentDTO.setSubjects(SubjectDTO.from(student.getSubjects()));
        return Response.ok(studentDTO).build();
    }

    @GET
    @Path("/{username}/subjects")
    public Response getStudentSubjects(@PathParam("username") String username) throws MyEntityNotFoundException {
        var student = studentBean.findWithSubjects(username);
        return Response.ok(SubjectDTO.from(student.getSubjects())).build();
    }

    @PUT
    @Path("/{username}")
    public Response updateStudent(@PathParam("username") String username, StudentDTO studentDTO) throws MyEntityNotFoundException {
        studentBean.update(
                username,
                studentDTO.getPassword(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getCourseCode()
        );
        Student updatedStudent = studentBean.find(username);
        return Response.ok(StudentDTO.from(updatedStudent)).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewStudent(StudentDTO studentDTO) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        studentBean.create(
                studentDTO.getUsername(),
                studentDTO.getPassword(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getCourseCode()
        );
        Student newStudent = studentBean.find(studentDTO.getUsername());
        return Response.status(Response.Status.CREATED).entity(StudentDTO.from(newStudent)).build();
    }

    @DELETE
    @Path("/{username}")
    public Response removeStudent(@PathParam("username") String username) throws MyEntityNotFoundException {
        Student removedStudent = studentBean.remove(username);
        return Response.ok(StudentDTO.from(removedStudent)).build();
    }
}