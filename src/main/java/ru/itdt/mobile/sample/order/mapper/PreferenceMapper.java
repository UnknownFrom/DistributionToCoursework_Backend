package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.PreferenceDTO;
import ru.itdt.mobile.sample.order.bean.response.OrderItemResponse;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PreferenceMapper {
    @Mappings({
            @Mapping(source = "student", target = "student"),
            @Mapping(source = "preferenceDTO.name", target = "name"),
            @Mapping(target = "id", ignore = true)
    })
    Preference mapDTOToEntity(PreferenceDTO preferenceDTO, Student student);
    OrderItemResponse mapToResponse(Coursework coursework);
}
