package com.team2.config;

import com.team2.security.JWTConfigurer;
import com.team2.security.JWTFilter;
import com.team2.security.JwtAuthenticationEntryPoint;
import com.team2.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity //Activa las notaciones @PreAuthorize
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JWTFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Habilitar CORS
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF en APIs REST

                .authorizeHttpRequests(authorize -> authorize
                        // Permitir acceso a los endpoints de registro y login sin autenticación
                        .requestMatchers(antMatcher("/auth/login")).permitAll()
                        .requestMatchers(antMatcher("/auth/register/reader")).permitAll()
                        .requestMatchers(antMatcher("/articles/recent")).permitAll()
                        .requestMatchers(antMatcher("/mail/**")).permitAll()
                        //.requestMatchers(antMatcher("/article/recent")).permitAll()

                        .requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated()
                )
                //.httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());
        //.httpBasic(Customizer.withDefaults()); // Utilizar autenticación básica HTTP para pruebas con Postman

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
