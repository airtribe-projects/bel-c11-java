package org.airtribe.AuthenticationAuthorizationC11.configuration;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.airtribe.AuthenticationAuthorizationC11.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authenticationHeader = request.getHeader("authorization");

    if (request.getRequestURI().contains("/register") ||
        request.getRequestURI().contains("/verifyRegistration") ||
        request.getRequestURI().contains("/signin")) {
      filterChain.doFilter(request, response);
      return;
    }

    if (authenticationHeader == null) {
     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     response.getWriter().write("Missing Authorization header");
     return;
    }

    if(!JwtUtil.validateJwtToken(authenticationHeader)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Invalid user token");
      return;
    }


    filterChain.doFilter(request, response);
  }
}
