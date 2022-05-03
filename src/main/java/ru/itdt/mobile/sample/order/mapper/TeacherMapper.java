package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.StudentDTO;
import ru.itdt.mobile.sample.order.bean.TeacherDTO;
import ru.itdt.mobile.sample.order.bean.request.StudentPostRequest;
import ru.itdt.mobile.sample.order.bean.request.TeacherPostRequest;
import ru.itdt.mobile.sample.order.domain.Student;
import ru.itdt.mobile.sample.order.domain.Teacher;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {
    Teacher mapDTOToEntity(TeacherDTO teacherDTO);
    TeacherDTO mapRequestToDTO(TeacherPostRequest teacherPostRequest);
}
