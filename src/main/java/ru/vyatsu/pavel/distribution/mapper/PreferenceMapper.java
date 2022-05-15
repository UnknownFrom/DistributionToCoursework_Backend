package ru.vyatsu.pavel.distribution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vyatsu.pavel.distribution.bean.request.PreferencePostRequest;
import ru.vyatsu.pavel.distribution.domain.Preference;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PreferenceMapper {
    Preference mapToEntity(PreferencePostRequest preferencePostRequest);
}
