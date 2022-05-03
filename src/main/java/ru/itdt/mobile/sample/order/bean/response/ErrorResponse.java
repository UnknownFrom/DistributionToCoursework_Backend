package ru.itdt.mobile.sample.order.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    @Schema(description = "Код ошибки", example = "Код ошибки")
    private int status;
    @Schema(description = "Сообщение ошибки", example = "Текст ошибки")
    private String message;
}