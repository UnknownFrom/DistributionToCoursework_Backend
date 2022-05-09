package ru.itdt.mobile.sample.order.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseworkPostRequest {
    @Schema(description = "Название курсовой", example = "Разработка приложения")
    private String name;
    private Long teacherId;
}
