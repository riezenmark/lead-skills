package org.omaewa.notastepik.service.api.util;

public final class PasswordEmailValidator {
    private static final String PASSWORD_REGEX;
    private static final String EMAIL_REGEX;

    static {
        PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,31}$";
        EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    }

    public static boolean passwordIsValid(final String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean emailIsValid(final String email) {
        return email.matches(EMAIL_REGEX);
    }
}
