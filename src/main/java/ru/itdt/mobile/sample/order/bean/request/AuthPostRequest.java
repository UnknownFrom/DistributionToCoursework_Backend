package ru.itdt.mobile.sample.order.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthPostRequest {
    @Schema(description = "Логин", example = "login")
    private String login;
    @Schema(description = "Пароль", example = "password")
    private String password;

}
