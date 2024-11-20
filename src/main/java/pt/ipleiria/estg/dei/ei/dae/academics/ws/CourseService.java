package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.CourseDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.CourseBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.academics.security.Authenticated;

import java.util.List;

@Path("course")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CourseService {

    @EJB
    private CourseBean courseBean;

    @GET
    @Path("/")
    public List<CourseDTO> getAllCourses() {
        return CourseDTO.from(courseBean.findAll());
    }

    @GET
    @Path("/{code}")
    public Response getCourse(@PathParam("code") long code) throws MyEntityNotFoundException {
        var course = courseBean.find(code);
        return Response.ok(CourseDTO.from(course)).build();
    }

    @POST
    @Path("/")
    @Authenticated
    @RolesAllowed({"Administrator"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewCourse(CourseDTO courseDTO) throws MyEntityExistsException, MyEntityNotFoundException {
        courseBean.create(
                courseDTO.getCode(),
                courseDTO.getName()
        );
        Course newCourse = courseBean.find(courseDTO.getCode());
        return Response.status(Response.Status.CREATED).entity(CourseDTO.from(newCourse)).build();
    }

    @PUT
    @Path("/{code}")
    public Response updateCourse(@PathParam("code") long code, CourseDTO courseDTO) throws MyEntityNotFoundException {
        courseBean.update(
                code,
                courseDTO.getName()
        );
        Course updatedCourse = courseBean.find(code);
        return Response.ok(CourseDTO.from(updatedCourse)).build();
    }

    @DELETE
    @Path("/{code}")
    public Response removeCourse(@PathParam("code") long code) throws MyEntityNotFoundException {
        Course removedCourse = courseBean.remove(code);
        return Response.ok(CourseDTO.from(removedCourse)).build();
    }
}
