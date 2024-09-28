package com.seek.testsgonzales.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JWTUtil {

  private final String secret;
  private final long expiration;

  public JWTUtil(@Value("${jwt.secret}") String secret,
      @Value("${jwt.expiration}") long expiration) {
    this.secret = secret;
    this.expiration = expiration;
  }

  /**
   * Extracts the username from the token
   *
   * @param token the token
   * @return the username
   */
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Extracts a claim from the token
   *
   * @param token          the token
   * @param claimsResolver the claims resolver
   * @param <T>            the type of the claim
   * @return the claim
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Extracts all claims from the token
   *
   * @param token the token
   * @return all claims
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  /**
   * Generates a token for the given username
   *
   * @param username the username
   * @return the token
   */
  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  /**
   * Creates a token
   *
   * @param claims  the claims
   * @param subject the subject
   * @return the token
   */
  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS256, secret).compact();
  }

  /**
   * Checks if the token is valid
   *
   * @param token the token
   * @return true if the token is valid, false otherwise
   */
  public Boolean isValidToken(String token) {
    try {
      if (StringUtils.isNotBlank(extractUsername(token))) {
        return true;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return false;
  }
}
