package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.AdministratorDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.AdministratorBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.List;

@Path("administrator")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AdministratorService {
    @EJB
    private AdministratorBean administratorBean;

    @GET
    @Path("/")
    public List<AdministratorDTO> getAllAdministrators() {
        return AdministratorDTO.from(administratorBean.findAll());
    }

    @GET
    @Path("/{username}")
    public Response getAdministrator(@PathParam("username") String username) throws MyEntityNotFoundException {
        var administrator = administratorBean.find(username);
        return Response.ok(AdministratorDTO.from(administrator)).build();
    }

    @PUT
    @Path("/{username}")
    public Response updateAdministrator(@PathParam("username") String username, AdministratorDTO administratorDTO) throws MyEntityNotFoundException {
        administratorBean.update(
                username,
                administratorDTO.getPassword(),
                administratorDTO.getName(),
                administratorDTO.getEmail()
        );
        Administrator updatedAdministrator = administratorBean.find(username);
        return Response.ok(AdministratorDTO.from(updatedAdministrator)).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAdministrator(AdministratorDTO administratorDTO) throws MyEntityNotFoundException, MyEntityExistsException {
        administratorBean.create(
                administratorDTO.getUsername(),
                administratorDTO.getPassword(),
                administratorDTO.getName(),
                administratorDTO.getEmail()
        );
        Administrator newAdministrator = administratorBean.find(administratorDTO.getUsername());
        return Response.status(Response.Status.CREATED).entity(AdministratorDTO.from(newAdministrator)).build();
    }

    @DELETE
    @Path("/{username}")
    public Response removeAdministrator(@PathParam("username") String username) throws MyEntityNotFoundException {
        Administrator removedAdministrator = administratorBean.remove(username);
        return Response.ok(AdministratorDTO.from(removedAdministrator)).build();
    }
}
