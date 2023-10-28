package com.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfigurer -> csrfConfigurer.ignoringRequestMatchers(new AntPathRequestMatcher("/api/**")))
                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/user/login")
                                .usernameParameter("email")
                                .defaultSuccessUrl("/")
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true))
        ;

        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        new AntPathRequestMatcher("/"),
                                        new AntPathRequestMatcher("/h2-console/**"),
                                        new AntPathRequestMatcher("/js/**"),
                                        new AntPathRequestMatcher("/css/**"),
                                        new AntPathRequestMatcher("/images/**"),
                                        new AntPathRequestMatcher("/images/**"),
                                        new AntPathRequestMatcher("/error"),
                                        new AntPathRequestMatcher("/user/**"),
                                        new AntPathRequestMatcher("/question/**")
                                ).permitAll()
                                .anyRequest().authenticated()
                )
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}