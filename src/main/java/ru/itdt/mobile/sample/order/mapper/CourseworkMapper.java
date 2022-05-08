package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.CourseworkDTO;
import ru.itdt.mobile.sample.order.bean.CourseworkShort;
import ru.itdt.mobile.sample.order.bean.request.CourseworkPostRequest;
import ru.itdt.mobile.sample.order.bean.response.CourseworkResponse;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Teacher;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseworkMapper {
    Coursework mapDTOToEntity(CourseworkDTO courseworkDTO);
    CourseworkDTO mapToDTO(Coursework coursework);
    CourseworkResponse mapEntityToResponse(Coursework coursework);
    @Mappings({
        @Mapping(target = "name", source = "courseworkPostRequest.name"),
    })
    CourseworkDTO mapRequestToDTO(CourseworkPostRequest courseworkPostRequest, Teacher teacher);
    @Mappings({
            @Mapping(target = "name", source = "courseworkPostRequest.name"),
    })
    Coursework mapRequestToEntity(CourseworkPostRequest courseworkPostRequest, Teacher teacher);
    CourseworkShort mapEntityToShort(Coursework coursework);
}
