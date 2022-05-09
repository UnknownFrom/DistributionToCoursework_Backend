package ru.itdt.mobile.sample.order.handler;

import ru.itdt.mobile.sample.order.bean.response.ErrorResponse;
import ru.itdt.mobile.sample.order.exception.SaveException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class SaveExceptionHandler implements ExceptionMapper<SaveException> {
    @Override
    public Response toResponse(SaveException exception) {
        return Response
                .status(BAD_REQUEST)
                .entity(new ErrorResponse(BAD_REQUEST.getStatusCode(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
