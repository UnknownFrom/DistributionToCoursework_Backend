package ru.vyatsu.pavel.distribution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.vyatsu.pavel.distribution.bean.CourseworkShort;
import ru.vyatsu.pavel.distribution.bean.PairStudentCoursework;
import ru.vyatsu.pavel.distribution.bean.StudentShort;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DistributionMapper {
    PairStudentCoursework mapToPair(StudentShort student, CourseworkShort coursework);
}
