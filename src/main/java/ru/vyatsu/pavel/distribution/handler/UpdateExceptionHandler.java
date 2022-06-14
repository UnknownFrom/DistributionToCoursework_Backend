package ru.vyatsu.pavel.distribution.handler;

import ru.vyatsu.pavel.distribution.bean.response.ErrorResponse;
import ru.vyatsu.pavel.distribution.exception.UpdateException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class UpdateExceptionHandler implements ExceptionMapper<UpdateException> {
    @Override
    public Response toResponse(UpdateException exception) {
        return Response
            .status(NOT_FOUND)
            .entity(new ErrorResponse(NOT_FOUND.getStatusCode(), exception.getMessage()))
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build();
    }
}
