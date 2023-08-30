// package com.atc.opportunity_management_system.configuration;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Service;

// import com.atc.opportunity_management_system.entity.User;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// @Service
// public class JwtUtil {
//     private String SECRET_KEY = "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";
//     public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

//     public String generateToken(User user){
//         Map<String, Object>claims = new HashMap<>();
//         return createToken(claims, user.getUsername());

//     }

//     private String createToken(Map<String, Object> claims, String username) {
//         return Jwts.builder()
//         .setClaims(claims)
//         .setSubject(username)
//         .setIssuedAt(new Date(System.currentTimeMillis()))
//         .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//         .signWith(SignatureAlgorithm.RS256, SECRET_KEY)
//         .compact();
//     }

//     public boolean validateToken(String token, UserDetails user){
//         final String username = extractUserName(token);
//         return (username.equals(user.getUsername()) && !isExpired(token));
//     }

//     private Claims extractAllClaims(String token){
//         return Jwts
//         .parser()
//         .setSigningKey(SECRET_KEY)
//         .parseClaimsJws(token)
//         .getBody();
//     }

//     public <T> T extractClaim(String token, Function<Claims,T> claimsResover){
//         final Claims claims = extractAllClaims(token);
//         return claimsResover.apply(claims);
//     }

//     public String extractUserName(String token){
//         return extractClaim(token, Claims::getSubject);
//     }
    
//     public Date extractExpiration(String token){
//         return extractClaim(token, Claims::getExpiration);
//     }
    
//     public boolean isExpired(String token){
//         return extractExpiration(token).before(new Date());
//     }
// }
