package org.airtribe.AuthenticationAuthorizationC11.configuration;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class AuthConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  public SecurityFilterChain securityFilterChainAuth(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authorizeRequests -> authorizeRequests.requestMatchers("/register", "verifyRegistration", "signin", "/tokenHello", "/hello")
                .permitAll()
                .anyRequest()
                .authenticated())
        .formLogin(formLogin -> formLogin.defaultSuccessUrl("/hello", true).permitAll())
        .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }
}
