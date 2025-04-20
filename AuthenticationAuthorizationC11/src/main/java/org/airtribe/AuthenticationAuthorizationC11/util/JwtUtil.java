package org.airtribe.AuthenticationAuthorizationC11.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;


public class JwtUtil {

  public static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  public static String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + 8 * 60 * 60 * 1000))
        .setIssuedAt(new Date())
        .signWith(secretKey)
        .compact();
  }

  public static boolean validateJwtToken(String authenticationHeader) {
      Claims claims = Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(authenticationHeader)
          .getBody();
      return claims.getExpiration().after(new Date());
  }
}
