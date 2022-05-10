package ru.vyatsu.pavel.distribution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vyatsu.pavel.distribution.bean.TeacherDTO;
import ru.vyatsu.pavel.distribution.bean.request.RegisterUserPostRequest;
import ru.vyatsu.pavel.distribution.bean.TeacherShort;
import ru.vyatsu.pavel.distribution.domain.Teacher;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {
    Teacher mapDTOToEntity(TeacherDTO teacherDTO);
    TeacherDTO mapToDTO(Teacher teacher);
    TeacherShort mapEntityToShort(Teacher teacher);
    TeacherDTO mapRequestToDTO(RegisterUserPostRequest registerUserPostRequest);
    Teacher mapRequestToEntity(RegisterUserPostRequest registerUserPostRequest);
}
