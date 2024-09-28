package com.seek.testsgonzales.config;

import com.seek.testsgonzales.security.filter.JWTRequestFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
@EnableWebSecurity
public class WebSecurityConfig {

  private final JWTRequestFilter jwtRequestFilter;

  public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    UserDetails user = User.builder().username("seek").password(encoder.encode("seek")).build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  AuthenticationManager authenticationManager(InMemoryUserDetailsManager userDetailsManager) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsManager);
    return new ProviderManager(provider);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(
            (authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/api/v1/login",
                "swagger-ui.html", "swagger-ui/**", "/v3/api-docs/**").permitAll())
        .authorizeHttpRequests(
            (authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/swagger-ui.html")
                .permitAll())
        .authorizeHttpRequests(
            (authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(HttpMethod.DELETE,
                "/api/v1/candidate/**").permitAll())
        .authorizeHttpRequests(
            (authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(HttpMethod.PUT,
                "/api/v1/candidate/**").permitAll())
        .authorizeHttpRequests(
            (authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/api/v1/candidate",
                "/api/v1/candidate/**").authenticated()).cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable);
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
