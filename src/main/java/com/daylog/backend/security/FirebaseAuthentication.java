package com.daylog.backend.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collections;

public class FirebaseAuthentication extends AbstractAuthenticationToken {
    private final String uid;
    private final String provider;
    private final String email;

    public FirebaseAuthentication(String uid, String provider, String email) {
        super(Collections.emptyList());
        this.uid = uid;
        this.provider = provider;
        this.email = email;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return uid;
    }

    public String getProvider() {
        return provider;
    }

    public String getEmail() {
        return email;
    }
}