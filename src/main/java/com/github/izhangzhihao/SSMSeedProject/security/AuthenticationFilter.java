package com.github.izhangzhihao.SSMSeedProject.security;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
//@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter("Password");
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("UserName");
    }

    @NotNull
    private String obtainValidateCode(HttpServletRequest request) {
        return request.getParameter("validateCode").toLowerCase();
    }

    public AuthenticationFilter() {
        super();
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String userValidateCode = obtainValidateCode(request);
        String serverValidateCode = request.getSession().getAttribute("validateCode").toString().toLowerCase();

        if (!userValidateCode.equals(serverValidateCode)) {
            throw new ValidateCodeNotMatchException("validate code not match");
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

//        log.warn(getAuthenticationManager().toString());
//
//        Authentication authenticate = getAuthenticationManager()
//                .authenticate(
//                        authRequest);

        return authRequest;
    }
}
