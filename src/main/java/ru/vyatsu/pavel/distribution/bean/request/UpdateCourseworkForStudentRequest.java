package ru.vyatsu.pavel.distribution.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseworkForStudentRequest {
    @Schema(description = "id курсовых работ, которыми студент хочет заниматься")
    private List<Long> selected;
    @Schema(description = "id курсовых работ, которыми студент не хочет заниматься")
    private List<Long> unselected;
}
