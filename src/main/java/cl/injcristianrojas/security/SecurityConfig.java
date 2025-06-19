package cl.injcristianrojas.security;

import java.util.Arrays;

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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;

import cl.injcristianrojas.security.jwt.JwtAuthenticationFilter;
import cl.injcristianrojas.security.jwt.MainUserDetailsService;
import jakarta.annotation.PostConstruct;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private WebApplicationContext applicationContext;
  @Autowired
  private MainUserDetailsService userDetailsService;
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @PostConstruct
  public void completeSetup() {
    userDetailsService = applicationContext.getBean(MainUserDetailsService.class);
  }

  @Bean
  public SecurityFilterChain apiV1SecurityFilterChain(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests(
            request -> request
                .requestMatchers("/healthcheck").permitAll()
                .requestMatchers("/h2/**", "/api/v1/**", "/api/v2/login", "/api-docs/**", "/swagger-ui/**")
                .permitAll().anyRequest().authenticated())
        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider()).addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .csrf(AbstractHttpConfigurer::disable)
        .headers(
            (headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable()))
        .cors(
            cors -> cors.configurationSource(request -> {
              CorsConfiguration configuration = new CorsConfiguration();
              configuration.setAllowedOrigins(Arrays.asList("*"));
              configuration.setAllowedMethods(Arrays.asList("*"));
              configuration.setAllowedHeaders(Arrays.asList("*"));
              return configuration;
            }));

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
