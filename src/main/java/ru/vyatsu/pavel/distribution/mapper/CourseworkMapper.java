package ru.vyatsu.pavel.distribution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vyatsu.pavel.distribution.bean.CourseworkDTO;
import ru.vyatsu.pavel.distribution.bean.CourseworkShort;
import ru.vyatsu.pavel.distribution.bean.request.CourseworkPostRequest;
import ru.vyatsu.pavel.distribution.bean.response.CourseworkResponse;
import ru.vyatsu.pavel.distribution.domain.Coursework;
import ru.vyatsu.pavel.distribution.domain.Teacher;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseworkMapper {
    Coursework mapDTOToEntity(CourseworkDTO courseworkDTO);
    CourseworkResponse mapEntityToResponse(Coursework coursework);
    @Mappings({
        @Mapping(target = "name", source = "courseworkPostRequest.name"),
    })
    CourseworkDTO mapRequestToDTO(CourseworkPostRequest courseworkPostRequest, Teacher teacher);
    CourseworkShort mapEntityToShort(Coursework coursework);
}
