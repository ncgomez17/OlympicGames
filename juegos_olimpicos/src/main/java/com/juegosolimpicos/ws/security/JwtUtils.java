package com.juegosolimpicos.ws.security;

import com.juegosolimpicos.openapi.model.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${olympicgames.app.jwtSecret}")
  private String jwtSecret;

  @Value("${olympicgames.app.jwtExpirationMs}")
  private int jwtExpirationMs;


  public String getJWTToken(UserDto userDto) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    userDto.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())));
    String token = Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(userDto.getNickName())
            .claim("authorities",
                    authorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
            .claim("id",userDto.getId())
            .claim("roles",userDto.getRole())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512,
                    jwtSecret.getBytes()).compact();

    return "Bearer " + token;
  }
  public UserDto parseToken(String token) {
    try {
      Claims body = Jwts.parser()
              .setSigningKey(jwtSecret)
              .parseClaimsJws(token)
              .getBody();

      UserDto userDto = new UserDto();
      userDto.setNickName(body.getSubject());
      userDto.setId(body.get("id").toString());
      userDto.setRole(Arrays.asList(body.get("roles").toString()));

      return userDto;

    } catch (JwtException | ClassCastException e) {
      return null;
    }
  }
  }
