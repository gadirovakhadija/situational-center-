//package org.example.situationalcenter.config;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.example.situationalcenter.entity.User;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import static org.example.situationalcenter.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
//import static org.example.situationalcenter.model.Constants.SIGNING_KEY;
//
//import java.io.Serializable;
//import io.jsonwebtoken.Claims;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenUtil implements Serializable {
//
//
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SIGNING_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public String generateToken(User user) {
//        return doGenerateToken(user.getEmail());
//    }
//
//    private String doGenerateToken(String subject) {
//
//        Claims claims = Jwts.claims().setSubject(subject);
//        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuer("http://devglan.com")
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
//                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
//                .compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        UserDetails userDetails1;
//        final String username = getUsernameFromToken(token);
//        return (
//                username.equals(userDetails.getUsername())
//                        && !isTokenExpired(token));
//    }
//
//}
