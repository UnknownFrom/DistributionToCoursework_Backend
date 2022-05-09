package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.StudentDTO;
import ru.itdt.mobile.sample.order.bean.StudentShort;
import ru.itdt.mobile.sample.order.bean.request.RegisterUserPostRequest;
import ru.itdt.mobile.sample.order.bean.response.AuthStudentResponse;
import ru.itdt.mobile.sample.order.domain.Student;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
    Student mapDTOToEntity(StudentDTO studentDTO);
    StudentDTO mapRequestToDTO(RegisterUserPostRequest registerUserPostRequest);
    Student mapRequestToEntity(RegisterUserPostRequest registerUserPostRequest);
    AuthStudentResponse mapEntityToResponse(Student student);
    StudentShort mapEntityToShort(Student student);
}
