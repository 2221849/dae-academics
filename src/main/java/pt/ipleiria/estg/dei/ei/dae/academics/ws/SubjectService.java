package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.TeacherDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.SubjectBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Path("subject")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SubjectService {
    @EJB
    private SubjectBean subjectBean;

    @GET
    @Path("/")
    public List<SubjectDTO> getAllSubjects() {
        return SubjectDTO.from(subjectBean.findAll());
    }

    @GET
    @Path("{code}")
    public Response getStudent(@PathParam("code") long code) {
        var subject = subjectBean.find(code);
        return Response.ok(SubjectDTO.from(subject)).build();
    }

    @GET
    @Path("{code}/students")
    public Response getSubjectStudents(@PathParam("code") long code) {
        var subject = subjectBean.findWithStudents(code);
        return Response.ok(StudentDTO.from(subject.getStudents())).build();
    }

    @GET
    @Path("{code}/teachers")
    public Response getSubjectTeachers(@PathParam("code") long code) {
        var subject = subjectBean.findWithTeachers(code);
        return Response.ok(TeacherDTO.from(subject.getTeachers())).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewSubject(SubjectDTO subjectDTO) {
        subjectBean.create(
                subjectDTO.getCode(),
                subjectDTO.getName(),
                subjectDTO.getScholarYear(),
                subjectDTO.getCourseYear(),
                subjectDTO.getCourseCode()
                );
        Subject newSubject = subjectBean.find(subjectDTO.getCode());
        return Response.status(Response.Status.CREATED).entity(SubjectDTO.from(newSubject)).build();
    }

    @DELETE
    @Path("/{code}")
    public Response removeSubject(@PathParam("code") long code) {
        Subject removedSubject = subjectBean.remove(code);
        if (removedSubject != null) {
            return Response.ok(SubjectDTO.from(removedSubject)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}