package com.vetimeline.api.infrastructure.auth;

import com.vetimeline.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${appConfig.appSecret}")
    private String appSecret;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Remove csrf protection
                .csrf().disable()
                // Custom JWT filter
                .addFilterAfter(
                        new JWTAuthorizationFilter(appSecret, userRepository),
                        UsernamePasswordAuthenticationFilter.class
                )
                // Public routes
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/auth/tokens").permitAll()
                .antMatchers(HttpMethod.GET, "/init_db").permitAll()
                // .antMatchers(HttpMethod.GET, "/newuser").permitAll()
                // Other routes must be authenticated
                .anyRequest().authenticated()
                // Authentication exceptions handler
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                // Setup stateless sessions
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS);
    }
}