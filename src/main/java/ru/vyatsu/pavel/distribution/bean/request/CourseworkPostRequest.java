package ru.vyatsu.pavel.distribution.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import ru.vyatsu.pavel.distribution.domain.Preference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseworkPostRequest {
    @Schema(description = "Название курсовой", example = "Разработка приложения")
    private String name;
    private String description;
    private List<Preference> preferences;
    private Long teacherId;
}
