package cl.injcristianrojas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.WebApplicationContext;

import cl.injcristianrojas.service.MainUserDetailsService;
import jakarta.annotation.PostConstruct;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private WebApplicationContext applicationContext;
  private MainUserDetailsService userDetailsService;

  @PostConstruct
  public void completeSetup() {
    userDetailsService = applicationContext.getBean(MainUserDetailsService.class);
  }

  @Bean
  public SecurityFilterChain apiV1SecurityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests((requests) -> requests
        .requestMatchers("/h2/**").permitAll()
        .requestMatchers("/api/v1/**").permitAll()
        .requestMatchers("/api/v2/**").permitAll()
        .requestMatchers("/api-docs/**").permitAll()
        .requestMatchers("/swagger-ui/**").permitAll()
        .anyRequest().authenticated()
    );

    http.csrf(AbstractHttpConfigurer::disable);
    http.headers(
      (headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable())
    );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

}