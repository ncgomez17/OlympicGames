package com.juegosolimpicos.ws.security;

import com.juegosolimpicos.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired UserServiceImpl userService;

  @Bean
  public JWTAuthorizationFilter authenticationJwtTokenFilter() {
    return new JWTAuthorizationFilter();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors()
        .and()
        .csrf()
        .disable()
        .addFilterAfter(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/user/authenticate")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/user/register")
        .permitAll()
        .antMatchers("/audit/**").hasRole("ADMIN")
        .anyRequest()
        .authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
      }
    };
  }
}
