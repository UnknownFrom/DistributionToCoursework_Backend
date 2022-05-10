package ru.vyatsu.pavel.distribution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vyatsu.pavel.distribution.bean.StudentDTO;
import ru.vyatsu.pavel.distribution.bean.StudentShort;
import ru.vyatsu.pavel.distribution.bean.request.RegisterUserPostRequest;
import ru.vyatsu.pavel.distribution.bean.response.AuthStudentResponse;
import ru.vyatsu.pavel.distribution.domain.Student;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
    Student mapDTOToEntity(StudentDTO studentDTO);
    StudentDTO mapRequestToDTO(RegisterUserPostRequest registerUserPostRequest);
    Student mapRequestToEntity(RegisterUserPostRequest registerUserPostRequest);
    AuthStudentResponse mapEntityToResponse(Student student);
    StudentShort mapEntityToShort(Student student);
}
