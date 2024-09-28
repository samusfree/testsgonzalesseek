package com.seek.testsgonzales.security.filter;

import com.seek.testsgonzales.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

  private final static String AUTHORIZATION_HEADER = "Authorization";
  private final static String BEARER = "Bearer ";
  private final JWTUtil jwtUtil;

  @Autowired
  public JWTRequestFilter(JWTUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  /**
   * This method is used to do the filter internally.
   *
   * @param request the request
   * @param response the response
   * @param filterChain the filter chain
   * @throws ServletException the servlet exception
   * @throws IOException the io exception
   */
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    if (hasAuthorizationBearer(request) && jwtUtil.isValidToken(getAccessToken(request))) {
      String token = getAccessToken(request);
      setAuthenticationContext(token, request);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * This method is used to check if the request has the authorization bearer.
   *
   * @param request the request
   * @return the boolean
   */
  private boolean hasAuthorizationBearer(HttpServletRequest request) {
    String header = request.getHeader(AUTHORIZATION_HEADER);
    return !ObjectUtils.isEmpty(header) && header.startsWith(BEARER);
  }

  /**
   * This method is used to get the access token from the request.
   *
   * @param request the request
   * @return the access token
   */
  private String getAccessToken(HttpServletRequest request) {
    String header = request.getHeader(AUTHORIZATION_HEADER);
    return StringUtils.substring(header, BEARER.length());
  }

  /**
   * This method is used to set the authentication context.
   *
   * @param token the token
   * @param request the request
   */
  private void setAuthenticationContext(String token, HttpServletRequest request) {
    UsernamePasswordAuthenticationToken
        authentication = new UsernamePasswordAuthenticationToken(getUserDetails(token), null, null);
    authentication.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  /**
   * This method is used to get the user details from the token.
   *
   * @param token the token
   * @return the user details
   */
  private User getUserDetails(String token) {
    String username = jwtUtil.extractUsername(token);
    return new User(username, StringUtils.EMPTY, new ArrayList<>());
  }
}
