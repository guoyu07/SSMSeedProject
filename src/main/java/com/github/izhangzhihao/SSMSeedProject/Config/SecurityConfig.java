package com.github.izhangzhihao.SSMSeedProject.Config;

import com.github.izhangzhihao.SSMSeedProject.Security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final Environment env;

    private final AuthenticationFilter authenticationFilter = new AuthenticationFilter();

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, Environment env) {
        this.userDetailsService = userDetailsService;
        this.env = env;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN", "USER")
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/Account/LogOut").permitAll()
                .antMatchers("/Account/**").anonymous()
                .antMatchers("/console/**").anonymous()

                .and()

                .formLogin()
                .loginPage("/Account/Login")
                .failureUrl("/Account/Login?error=true")
                .defaultSuccessUrl("/")

                .and()
                .httpBasic()

                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .rememberMe()
                .tokenValiditySeconds(604800)//记住我一周
                .key("RememberMe");

        String[] activeProfiles = env.getActiveProfiles();
        if (activeProfiles != null && activeProfiles.length > 0) {
            if (activeProfiles[0].equals("development")) {
                http
                        .csrf()
                        .disable();

                http.headers()
                        .frameOptions()
                        .disable();

            } else if (activeProfiles[0].equals("production")) {

            }
        }
    }
}
