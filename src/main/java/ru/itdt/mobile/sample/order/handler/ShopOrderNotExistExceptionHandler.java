package ru.itdt.mobile.sample.order.handler;

import ru.itdt.mobile.sample.order.bean.response.ErrorResponse;
import ru.itdt.mobile.sample.order.exception.OrderItemNotExistException;
import ru.itdt.mobile.sample.order.exception.ShopOrderNotExistException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class ShopOrderNotExistExceptionHandler implements ExceptionMapper<ShopOrderNotExistException> {
    @Override
    public Response toResponse(ShopOrderNotExistException exception) {
        return Response
                .status(NOT_FOUND)
                .entity(new ErrorResponse(NOT_FOUND.getStatusCode(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
