package ru.itdt.mobile.sample.order.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import ru.itdt.mobile.sample.order.domain.Coursework;
import ru.itdt.mobile.sample.order.domain.Preference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseworkForStudentRequest {
    @Schema(implementation = UpdateCourseworkForStudentRequest.class, description = "Предпочтения")
    private List<Long> selected;
    private List<Long> unselected;
}
