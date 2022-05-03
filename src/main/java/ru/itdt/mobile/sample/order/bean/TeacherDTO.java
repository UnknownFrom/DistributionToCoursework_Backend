package ru.itdt.mobile.sample.order.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itdt.mobile.sample.order.domain.Preference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {
    private String name;
    private String login;
    private String password;
}
