package pt.ipleiria.estg.dei.ei.dae.academics.exceptions.mappers;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.util.logging.Logger;

public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {
    private static final Logger logger = Logger.getLogger(Exception.class.getCanonicalName());

    @Override
    public Response toResponse(ForbiddenException e) {
        String errorMsg = e.getMessage();
        logger.warning("FORBIDDEN: " + errorMsg);
        return Response.status(Response.Status.FORBIDDEN).entity(errorMsg).build();
    }
}
