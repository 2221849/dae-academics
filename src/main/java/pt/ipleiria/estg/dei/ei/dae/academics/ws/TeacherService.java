package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.TeacherDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.TeacherBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Teacher;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.List;

@Path("teacher")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TeacherService {
    @EJB
    private TeacherBean teacherBean;

    @GET
    @Path("/")
    public List<TeacherDTO> getAllTeacher() {
        return TeacherDTO.from(teacherBean.findAll());
    }

    @GET
    @Path("{username}")
    public Response getTeacher(@PathParam("username") String username) throws MyEntityNotFoundException {
        var teacher = teacherBean.find(username);
        return Response.ok(TeacherDTO.from(teacher)).build();
    }

    @GET
    @Path("{username}/subjects")
    public Response getTeacherSubjects(@PathParam("username") String username) throws MyEntityNotFoundException {
        var teacher = teacherBean.findWithSubjects(username);
        return Response.ok(SubjectDTO.from(teacher.getSubjects())).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewTeacher(TeacherDTO teacherDTO) throws MyEntityExistsException, MyEntityNotFoundException {
        teacherBean.create(
                teacherDTO.getUsername(),
                teacherDTO.getPassword(),
                teacherDTO.getName(),
                teacherDTO.getEmail(),
                teacherDTO.getOffice()
        );
        Teacher newTeacher = teacherBean.find(teacherDTO.getUsername());
        return Response.status(Response.Status.CREATED).entity(TeacherDTO.from(newTeacher)).build();
    }

    @DELETE
    @Path("/{username}")
    public Response removeTeacher(@PathParam("username") String username) throws MyEntityNotFoundException {
        Teacher removedTeacher = teacherBean.remove(username);
        return Response.ok(TeacherDTO.from(removedTeacher)).build();
    }
}
