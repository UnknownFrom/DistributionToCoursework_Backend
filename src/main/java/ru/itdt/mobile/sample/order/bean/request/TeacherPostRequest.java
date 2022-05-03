package ru.itdt.mobile.sample.order.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import ru.itdt.mobile.sample.order.bean.PreferenceDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPostRequest {
    @Schema(description = "Общая стоймость заказа", example = "1000000")
    private String name;
    @Schema(description = "Логин", example = "login")
    private String login;
    @Schema(description = "Пароль", example = "password")
    private String password;
}
