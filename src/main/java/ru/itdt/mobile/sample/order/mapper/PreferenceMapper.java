package ru.itdt.mobile.sample.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.itdt.mobile.sample.order.bean.PreferenceDTO;
import ru.itdt.mobile.sample.order.bean.request.PreferencePostRequest;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;
import ru.itdt.mobile.sample.order.domain.Student;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PreferenceMapper {
    Preference mapDTOToEntity(PreferenceDTO preferenceDTO);
    PreferenceDTO mapToDTO(PreferencePostRequest preferencePostRequest);
    Preference mapToEntity(PreferencePostRequest preferencePostRequest);
}
