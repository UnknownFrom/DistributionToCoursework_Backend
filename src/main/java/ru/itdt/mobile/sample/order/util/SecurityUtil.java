package ru.itdt.mobile.sample.order.util;

import ru.itdt.mobile.sample.order.exception.InvalidTokenException;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SecurityUtil {
    private SecurityUtil() {
    }

    public static long extractUserId(SecurityContext sc) {
        final Principal principal = sc.getUserPrincipal();
        if (principal == null || principal.getName() == null || !principal.getName().matches("^\\d+$")) {
            throw new InvalidTokenException("Не удалось получить id пользователя из токена авторизации");
        }
        return Long.parseLong(principal.getName());
    }
}
