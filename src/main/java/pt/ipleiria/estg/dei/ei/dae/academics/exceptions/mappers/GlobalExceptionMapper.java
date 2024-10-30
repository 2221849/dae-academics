package pt.ipleiria.estg.dei.ei.dae.academics.exceptions.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger logger = Logger.getLogger(Exception.class.getCanonicalName());

    @Override
    public Response toResponse(Exception e) {
        String errorMsg = e.getMessage();
        logger.warning("INTERNAL_SERVER_ERROR: " + errorMsg);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMsg).build();
    }
}
