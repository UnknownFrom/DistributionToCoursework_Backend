package ru.itdt.mobile.sample.order.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferencePostRequest {
    @Schema(description = "Название предпочтения", example = "Программирование")
    private String name;
}
