package com.github.izhangzhihao.SSMSeedProject.security;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeNotMatchException extends AuthenticationException {
    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public ValidateCodeNotMatchException(String msg) {
        super(msg);
    }
}
