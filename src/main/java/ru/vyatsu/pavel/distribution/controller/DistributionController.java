package ru.vyatsu.pavel.distribution.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import ru.vyatsu.pavel.distribution.bean.CourseworkShort;
import ru.vyatsu.pavel.distribution.bean.PairStudentCoursework;
import ru.vyatsu.pavel.distribution.bean.StudentShort;
import ru.vyatsu.pavel.distribution.bean.TeacherShort;
import ru.vyatsu.pavel.distribution.bean.request.*;
import ru.vyatsu.pavel.distribution.bean.response.CourseworkResponse;
import ru.vyatsu.pavel.distribution.bean.response.ErrorResponse;
import ru.vyatsu.pavel.distribution.bean.response.StudentResponse;
import ru.vyatsu.pavel.distribution.domain.Preference;
import ru.vyatsu.pavel.distribution.service.DistributionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class DistributionController {

    @Inject
    DistributionService distributionService;

    @POST
    @Path("/student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить студента")
    @APIResponses({
        @APIResponse(description = "Студент сохранён", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentShort.class))),
        @APIResponse(description = "Ошибка при сохрании студента",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response saveStudent(@RequestBody(
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterUserPostRequest.class))) final RegisterUserPostRequest registerUserPostRequest) {
        return Response.ok(distributionService.saveStudent(registerUserPostRequest)).build();
    }

    @POST
    @Path("/teacher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить преподавателя")
    @APIResponses({
        @APIResponse(description = "Преподаватель сохранён", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherShort.class))),
        @APIResponse(description = "Ошибка при сохрании преподавателя",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response saveTeacher(@RequestBody(
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterUserPostRequest.class))) final RegisterUserPostRequest registerUserPostRequest) {
        return Response.ok(distributionService.saveTeacher(registerUserPostRequest)).build();
    }

    @POST
    @Path("/preference")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Сохранить предпочтение")
    @APIResponses({
        @APIResponse(description = "Предпочтение сохранено", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
        @APIResponse(description = "Ошибка при сохрании предпочтения",
            responseCode = "40*",
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
    @Operation(description = "Сохранить курсовую")
    @APIResponses({
        @APIResponse(description = "Курсовая сохранена", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkShort.class))),
        @APIResponse(description = "Ошибка при сохрании курсовой",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response saveCoursework(@RequestBody(
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkPostRequest.class))) final CourseworkPostRequest courseworkPostRequest) {
        return Response.ok(distributionService.saveCoursework(courseworkPostRequest)).build();
    }

    @PUT
    @Path("/student/{studentId}/coursework")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Изменить список выбранных студентом курсовых")
    @APIResponses({
        @APIResponse(description = "Список выбранных курсовых изменён", responseCode = "200"),
        @APIResponse(description = "Ошибка при обновлении списка выбранных курсовых",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response updateCourseworkForStudent(@RequestBody(
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateCourseworkForStudentRequest.class))) final UpdateCourseworkForStudentRequest updateCourseworkForStudentRequest,
                                               @PathParam("studentId") final long studentId) {
        distributionService.updateCourseworkForStudent(updateCourseworkForStudentRequest, studentId);
        return Response.ok().build();
    }

    @PUT
    @Path("/coursework")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Изменить данные курсовой")
    @APIResponses({
        @APIResponse(description = "Данные курсовой изменены", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkResponse.class))),
        @APIResponse(description = "Ошибка при изменении курсовой",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response updateCoursework(@RequestBody(
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateCoursework.class))) final UpdateCoursework updateCoursework) {
        return Response.ok(distributionService.updateCoursework(updateCoursework)).build();
    }

    @POST
    @Path("/authStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Авторизация студента")
    @APIResponses({
        @APIResponse(description = "Студент авторизован", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentResponse.class))),
        @APIResponse(description = "Ошибка при авторизации",
            responseCode = "40*",
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
    @Operation(description = "Авторизация преподавателя")
    @APIResponses({
        @APIResponse(description = "Преподаватель авторизован", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherShort.class))),
        @APIResponse(description = "Ошибка при авторизации",
            responseCode = "40*",
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
        @APIResponse(description = "Предпочтительный руководителя для студента обновлён",
            responseCode = "200"),
        @APIResponse(description = "Ошибка при обновлении",
            responseCode = "40*",
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
        @APIResponse(description = "Предпочтения для студента обновлены",
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePreferencesRequest.class))),
        @APIResponse(description = "Ошибка при обновлении",
            responseCode = "40*",
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
    @Operation(description = "Обновить предпочтения для курсовой работы")
    @APIResponses({
        @APIResponse(description = "Предпочтения для курсовой работы обновлены",
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePreferencesRequest.class))),
        @APIResponse(description = "Ошибка при обновлении",
            responseCode = "40*",
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
    @Operation(description = "Получить список всех преподавателей")
    @APIResponses({
        @APIResponse(description = "Список преподавателей получен", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherShort.class))),
        @APIResponse(description = "Ошибка при попытке получить список преподавателей",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllTeachers() {
        return Response.ok(distributionService.getAllTeachers()).build();
    }

    @GET
    @Path("/teacher/{teacherId}/courseworks")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить список курсовых работ, принадлежащих преподавателю")
    @APIResponses({
        @APIResponse(description = "Список курсовых работ получен", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkShort.class))),
        @APIResponse(description = "Ошибка при попытке получить список курсовых работ преподавателя",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getCourseworksForTeachers(@PathParam("teacherId") final long teacherId) {
        return Response.ok(distributionService.getCourseworkForTeacher(teacherId)).build();
    }

    @GET
    @Path("/coursework/{courseworkId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить курсовую работу")
    @APIResponses({
        @APIResponse(description = "Курсовая работа получена", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkResponse.class))),
        @APIResponse(description = "Ошибка при попытке получить курсовую работу",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getCoursework(@PathParam("courseworkId") final long courseworkId) {
        return Response.ok(distributionService.getCoursework(courseworkId)).build();
    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить список всех студентов")
    @APIResponses({
        @APIResponse(description = "Список студентов получен", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentShort.class))),
        @APIResponse(description = "Ошибка при попытке получить список студентов",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllStudents() {
        return Response.ok(distributionService.getAllStudents()).build();
    }

    @GET
    @Path("/student/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить список всех студентов")
    @APIResponses({
        @APIResponse(description = "Список студентов получен", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentShort.class))),
        @APIResponse(description = "Ошибка при попытке получить список студентов",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getStudent(@PathParam("studentId") final long studentId) {
        return Response.ok(distributionService.getStudent(studentId)).build();
    }

    @GET
    @Path("/preferences")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить список всех предпочтений")
    @APIResponses({
        @APIResponse(description = "Список предпочтений получен", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Preference.class))),
        @APIResponse(description = "Ошибка при попытке получить список предпочтений",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getAllPreferences() {
        return Response.ok(distributionService.getAllPreferences()).build();
    }

    @GET
    @Path("/courseworks")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить список всех курсовых работ")
    @APIResponses({
        @APIResponse(description = "Список курсовых работ получен", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseworkResponse.class))),
        @APIResponse(description = "Ошибка при попытке получить список курсовых работ",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getCourseworks() {
        return Response.ok(distributionService.getAllCoursework()).build();
    }

    @GET
    @Path("/distribution")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(description = "Получить распределение курсовых работ по студентам")
    @APIResponses({
        @APIResponse(description = "Распределение курсовых работ по студентам получено", responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PairStudentCoursework.class))),
        @APIResponse(description = "Ошибка при получении распределения курсовых работ по студентам",
            responseCode = "40*",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getResultDistribution() {
        return Response.ok(distributionService.getResultDistribution()).build();
    }
}
