package ru.vyatsu.pavel.distribution.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vyatsu.pavel.distribution.domain.Preference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    private String name;
    private String login;
    private String password;
    private List<Preference> preferences;
}
