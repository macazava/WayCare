package com.example.waycare.Security;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(
                                    "/api/utilizadores/register",
                                    "/api/utilizadores/login",
                                    "/api/utilizadores/recuperar/**",
                                    "/api/utilizadores/reset-password"
                            ).permitAll()
                            .anyRequest().permitAll()
                    );

            return http.build();
        }
    }

