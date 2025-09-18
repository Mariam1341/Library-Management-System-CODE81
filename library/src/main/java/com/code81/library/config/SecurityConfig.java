package com.code81.library.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/librarian/**").hasAnyAuthority("ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/categories/**").hasAnyAuthority("ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/books/**").hasAnyAuthority("ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/publishers/**").hasAnyAuthority("ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/authors/**").hasAnyAuthority("ADMIN", "LIBRARIAN")
                        .requestMatchers("/api/staff/**").hasAnyAuthority("ADMIN", "LIBRARIAN", "STAFF")
                        .requestMatchers("/api/members/**").hasAnyAuthority("ADMIN", "LIBRARIAN", "STAFF")
                        .requestMatchers("/api/transactions/**").hasAnyAuthority("ADMIN", "LIBRARIAN", "STAFF")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
