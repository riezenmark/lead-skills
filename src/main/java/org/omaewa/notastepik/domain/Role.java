package org.omaewa.notastepik.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT, MEMBER, TUTOR, ADMIN;

    public String getAuthority() {
        return name();
    }
}
