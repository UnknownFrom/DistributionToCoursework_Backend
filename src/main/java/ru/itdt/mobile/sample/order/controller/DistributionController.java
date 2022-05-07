package ru.itdt.mobile.sample.order.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import ru.itdt.mobile.sample.order.bean.PreferenceDTO;
import ru.itdt.mobile.sample.order.bean.TeacherDTO;
import ru.itdt.mobile.sample.order.bean.request.*;
import ru.itdt.mobile.sample.order.bean.response.ErrorResponse;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.service.DistributionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@SecurityScheme(securitySchemeName = "userJwtTokenAuth", description = "Аутентификация обычного пользователя",
        type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Path("/")
public class DistributionController {

    @Inject
    DistributionService distributionService;

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
        return Response.ok(distributionService.saveStudent(studentPostRequest)).build();
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
        return Response.ok(distributionService.saveTeacher(teacherPostRequest)).build();
    }

    @POST
    @Path("/preference")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response savePreference(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PreferencePostRequest.class))) final PreferencePostRequest preferencePostRequest) {
        return Response.ok(distributionService.savePreference(preferencePostRequest)).build();
    }

    @POST
    @Path("/coursework")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response saveCoursework(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkPostRequest.class))) final CourseworkPostRequest courseworkPostRequest) {
        return Response.ok(distributionService.saveCoursework(courseworkPostRequest)).build();
    }

   /* @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response test(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PreferenceDTO.class))) final PreferenceDTO preferenceDTO,
                         @Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                         @Context final SecurityContext sc) {
        //distributionService.test();
        return Response.ok(distributionService.test()).build();
    }*/

    @POST
    @Path("/authStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response authStudent(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthPostRequest.class))) final AuthPostRequest authPostRequest) {
        return Response.ok(distributionService.authStudent(authPostRequest)).build();
    }

    @POST
    @Path("/authTeacher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить заказ")
    @APIResponses({
            @APIResponse(description = "Заказ сохранён", responseCode = "200"),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response authTeacher(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthPostRequest.class))) final AuthPostRequest authPostRequest) {
        return Response.ok(distributionService.authTeacher(authPostRequest)).build();
    }

    @PUT
    @Path("/student/{studentId}/preferredTeacher/{teacherId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Обновить предпочтительного руководителя для студента")
    @APIResponses({
            @APIResponse(description = "Адрес доставки обновлён",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))),
            @APIResponse(description = "Неудачный результат обновления, так как адреса с таким id не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response updatePreferredTeacherForStudent(@PathParam("studentId") final long studentId, @PathParam("teacherId") final long teacherId) {
        distributionService.updatePreferredTeacherForStudent(studentId, teacherId);
        return Response
                .ok()
                .build();
    }

    @PUT
    @Path("/student/{studentId}/preferences")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Обновить предпочтения для студента")
    @APIResponses({
            @APIResponse(description = "Адрес доставки обновлён",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePreferencesRequest.class))),
            @APIResponse(description = "Неудачный результат обновления, так как адреса с таким id не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response updatePreferencesForStudent(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePreferencesRequest.class))) final UpdatePreferencesRequest updatePreferencesRequest, @PathParam("studentId") final long studentId) {

        distributionService.updatePreferencesForStudent(updatePreferencesRequest.getPreferences(), studentId);
        return Response
                .ok()
                .build();
    }

    @PUT
    @Path("/coursework/{courseworkId}/preferences")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Обновить предпочтения для студента")
    @APIResponses({
            @APIResponse(description = "Адрес доставки обновлён",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePreferencesRequest.class))),
            @APIResponse(description = "Неудачный результат обновления, так как адреса с таким id не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response updatePreferencesForCoursework(@RequestBody(
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePreferencesRequest.class))) final UpdatePreferencesRequest updatePreferencesRequest, @PathParam("courseworkId") final long courseworkId) {

        distributionService.updatePreferencesForCoursework(updatePreferencesRequest.getPreferences(), courseworkId);
        return Response
                .ok()
                .build();
    }



    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить всех учителей")
    @APIResponses({
            @APIResponse(description = "Учителя получены", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllTeachers(@Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                   @Context final SecurityContext sc) {
        return Response.ok(distributionService.getAllTeachers()).build();
    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить всех учителей")
    @APIResponses({
            @APIResponse(description = "Учителя получены", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDTO.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllStudents(@Parameter(in = ParameterIn.HEADER, required = true, name = HttpHeaders.AUTHORIZATION)
                                   @Context final SecurityContext sc) {
        return Response.ok(distributionService.getAllStudents()).build();
    }

    @GET
    @Path("/preferences")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить всех учителей")
    @APIResponses({
            @APIResponse(description = "Учителя получены", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllPreferences() {
        return Response.ok(distributionService.getAllPreferences()).build();
    }

    @GET
    @Path("/allCoursework")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить всех учителей")
    @APIResponses({
            @APIResponse(description = "Учителя получены", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllCoursework() {
        return Response.ok(distributionService.getAllCoursework()).build();
    }


    @GET
    @Path("/distribution")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить всех учителей")
    @APIResponses({
            @APIResponse(description = "Учителя получены", responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
            @APIResponse(description = "Не удалось получить, так как заказа с таким id у данного пользователя не существует",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @APIResponse(description = "Некорректный токен",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getResultDistribution() {
        return Response.ok(distributionService.getResultDistribution()).build();
    }


}
