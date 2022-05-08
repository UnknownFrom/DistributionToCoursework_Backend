package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.TeacherDTO;
import ru.itdt.mobile.sample.order.bean.request.SaveUserPostRequest;
import ru.itdt.mobile.sample.order.bean.response.TeacherResponse;
import ru.itdt.mobile.sample.order.domain.Teacher;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {
    Teacher mapDTOToEntity(TeacherDTO teacherDTO);
    TeacherDTO mapToDTO(Teacher teacher);
    TeacherResponse mapEntityToResponse(Teacher teacher);
    TeacherDTO mapRequestToDTO(SaveUserPostRequest saveUserPostRequest);
    Teacher mapRequestToEntity(SaveUserPostRequest saveUserPostRequest);
}
