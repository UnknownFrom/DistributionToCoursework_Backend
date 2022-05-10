package ru.vyatsu.pavel.distribution.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vyatsu.pavel.distribution.domain.Preference;
import ru.vyatsu.pavel.distribution.domain.Teacher;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseworkDTO {
    private String name;
    private List<Preference> preferences;
    private Teacher teacher;
}
