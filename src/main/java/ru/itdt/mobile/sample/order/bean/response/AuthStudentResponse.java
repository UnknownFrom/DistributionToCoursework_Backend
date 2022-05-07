package ru.itdt.mobile.sample.order.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itdt.mobile.sample.order.domain.Preference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthStudentResponse {
    private Long id;
    private String name;
    private List<Preference> preferences;
    private TeacherResponse teacher;
}
