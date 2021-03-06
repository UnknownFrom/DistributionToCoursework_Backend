package ru.vyatsu.pavel.distribution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vyatsu.pavel.distribution.bean.StudentShort;
import ru.vyatsu.pavel.distribution.bean.request.RegisterUserPostRequest;
import ru.vyatsu.pavel.distribution.bean.response.StudentResponse;
import ru.vyatsu.pavel.distribution.domain.Student;

@Mapper(componentModel = "cdi",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
    Student mapRequestToEntity(RegisterUserPostRequest registerUserPostRequest);

    StudentResponse mapEntityToResponse(Student student);

    StudentShort mapEntityToShort(Student student);
}
