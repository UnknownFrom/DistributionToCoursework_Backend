package ru.vyatsu.pavel.distribution.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vyatsu.pavel.distribution.bean.TeacherShort;
import ru.vyatsu.pavel.distribution.domain.Preference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseworkResponse {
    private Long id;
    private String name;
    private String description;
    private List<Preference> preferences;
    private TeacherShort teacher;
}
