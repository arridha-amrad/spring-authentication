package com.arridhaamrad.authentication.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

   @Value("${spring.app.jwt-secret}")
   private String jwtSecret;

   @Value("${spring.app.name}")
   private String appName;

   @Value("${spring.app.jwt-expiration-ms}")
   private int jwtExpirationMs;

   public String generateTokenWithUsername(String username){
      return Jwts.builder()
          .setSubject(username)
          .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
          .setIssuer(appName)
          .setIssuedAt(new Date())
          .signWith(SignatureAlgorithm.HS512, jwtSecret)
          .compact();
   }

   public String getUsernameFromToken(String token){
      return Jwts.parser()
          .setSigningKey(jwtSecret)
          .parseClaimsJws(token)
          .getBody()
          .getSubject();
   }

   public boolean validateJwtToken(String token){
      try {
         Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
         return true;
      } catch (SignatureException exception){
         System.out.println("Invalid JWT Signature " + exception.getMessage());
      } catch (MalformedJwtException exception){
         System.out.println("Invalid JWT Token " + exception.getMessage());
      } catch (ExpiredJwtException exception){
         System.out.println("Jwt Expired " + exception.getMessage());
      } catch (UnsupportedJwtException exception){
         System.out.println("JWT token is unsupported " + exception.getMessage());
      } catch (IllegalArgumentException exception){
         System.out.println("JWT claims is empty " + exception.getMessage());
      }
      return false;
   }

}
