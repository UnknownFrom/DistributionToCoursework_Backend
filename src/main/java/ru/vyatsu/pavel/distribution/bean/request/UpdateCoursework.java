package ru.vyatsu.pavel.distribution.bean.request;

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
public class UpdateCoursework {
    private Long id;
    private String name;
    private String description;
    private List<Preference> preferences;
    private Long teacherId;
}
