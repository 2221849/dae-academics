package pt.ipleiria.estg.dei.ei.dae.academics.exceptions.mappers;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.util.logging.Logger;

public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {
    private static final Logger logger = Logger.getLogger(Exception.class.getCanonicalName());

    @Override
    public Response toResponse(NotAuthorizedException e) {
        String errorMsg = e.getMessage();
        logger.warning("UNAUTHORIZED: " + errorMsg);
        return Response.status(Response.Status.UNAUTHORIZED).entity(errorMsg).build();
    }
}
