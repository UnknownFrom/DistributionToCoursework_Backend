package ru.itdt.mobile.sample.order.controller;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import ru.itdt.mobile.sample.order.bean.request.StudentPostRequest;
import ru.itdt.mobile.sample.order.bean.request.TeacherPostRequest;
import ru.itdt.mobile.sample.order.bean.response.ErrorResponse;
import ru.itdt.mobile.sample.order.bean.response.OrderItemResponse;
import ru.itdt.mobile.sample.order.service.DistributionService;
import ru.itdt.mobile.sample.order.util.SecurityUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@SecurityScheme(securitySchemeName = "userJwtTokenAuth", description = "Аутентификация обычного пользователя",
        type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Path("/")
public class DistributionController {

    @Inject
    DistributionService distributionService;

 /*   @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить все заказы пользователя")
    @APIResponses({
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "userJwtTokenAuth")
    @Authenticated
    public Response getShopOrders(@Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                  @Context final SecurityContext sc) {
        return Response.ok(distributionService.getAllUserOrders(SecurityUtil.extractUserId(sc))).build();
    }

    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить заказ по id заказа")
    @APIResponses({
            @APIResponse(description = "Требуемый заказ получен", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShopOrderResponse.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "userJwtTokenAuth")
    @Authenticated
    public Response getShopOrderById(@Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                     @Context final SecurityContext sc,
                                     @PathParam("id") final long orderId) {
        distributionService.checkAccess(orderId, SecurityUtil.extractUserId(sc));
        return Response.ok(distributionService.getShopOrderById(orderId)).build();
    }

    @GET
    @Path("/orders/{id}/products")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить все товары указанного заказа")
    @APIResponses({
            @APIResponse(description = "Требуемые товары получены", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemResponse.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "userJwtTokenAuth")
    @Authenticated
    public Response getOrderGoods(@Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                  @Context final SecurityContext sc,
                                  @PathParam("id") final long orderId) {
        distributionService.checkAccess(orderId, SecurityUtil.extractUserId(sc));
        return Response.ok(distributionService.getOrderItems(orderId)).build();
    }

    @GET
    @Path("/orders/{orderId}/products/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить указанный товар нужного заказа")
    @APIResponses({
            @APIResponse(description = "Требуемый товар получен", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemResponse.class))),
            @APIResponse(description = "Не удалось получить, так как товара с таким id у данного заказа не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @SecurityRequirement(name = "userJwtTokenAuth")
    @Authenticated
    public Response getOrderGoodById(@Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                     @Context final SecurityContext sc,
                                     @PathParam("orderId") final long orderId,
                                     @PathParam("productId") final long productId) {
        distributionService.checkAccess(orderId, SecurityUtil.extractUserId(sc));
        return Response.ok(distributionService.getOrderItem(orderId, productId)).build();
    }*/

    @POST
    @Path("/student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response saveStudent(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentPostRequest.class))) final StudentPostRequest studentPostRequest,
                                  @Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                  @Context final SecurityContext sc) {
        distributionService.saveStudent(studentPostRequest);
        return Response.ok().build();
    }

    @POST
    @Path("/teacher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response saveTeacher(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentPostRequest.class))) final TeacherPostRequest teacherPostRequest,
                                  @Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                  @Context final SecurityContext sc) {
        distributionService.saveTeacher(teacherPostRequest);
        return Response.ok().build();
    }
}
