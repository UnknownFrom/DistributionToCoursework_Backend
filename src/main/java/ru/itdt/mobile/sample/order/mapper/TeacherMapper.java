package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.TeacherDTO;
import ru.itdt.mobile.sample.order.bean.request.RegisterUserPostRequest;
import ru.itdt.mobile.sample.order.bean.TeacherShort;
import ru.itdt.mobile.sample.order.domain.Teacher;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {
    Teacher mapDTOToEntity(TeacherDTO teacherDTO);
    TeacherDTO mapToDTO(Teacher teacher);
    TeacherShort mapEntityToShort(Teacher teacher);
    TeacherDTO mapRequestToDTO(RegisterUserPostRequest registerUserPostRequest);
    Teacher mapRequestToEntity(RegisterUserPostRequest registerUserPostRequest);
}
