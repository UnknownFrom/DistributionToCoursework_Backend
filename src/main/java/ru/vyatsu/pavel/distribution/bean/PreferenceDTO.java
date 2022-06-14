package ru.vyatsu.pavel.distribution.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferenceDTO {
    @Schema(description = "Название предпочтения", example = "Программирование")
    private String name;
}