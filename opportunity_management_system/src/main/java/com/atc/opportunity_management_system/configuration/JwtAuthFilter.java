package com.atc.opportunity_management_system.configuration;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.RoleRepository;
import com.atc.opportunity_management_system.repository.UserRepository;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
    

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        
        String username = null;
        String token = null;
        String userEmail = null;
        String[] name = null;
        JWTClaimsSet claims = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            token = requestHeader.substring(7);
            try {
                JWT jwt = JWTParser.parse(token);
                claims = jwt.getJWTClaimsSet();
                name = claims.getStringClaim("name").split(" ");
                userEmail = claims.getStringClaim("email");
                System.out.println();
                
                username = claims.getStringClaim("email").split("@")[0];

            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                System.out.println("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                System.out.println("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }


        } else {
            System.out.println("Invalid Header Value !! ");
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
            User user = (User) this.userDetailsService.loadUserByUsername(username);
            if(user==null){
                user = new User();
                user.setUsername(username);
                user.setFirstName(name[0]);
                user.setLastName(name.length > 1 ? name[1] : "");
                user.setEmail(userEmail);
                user.setBbdBucks(0);
                user.setActive(true);
                user.setRole(roleRepository.findByRole("ROLE_USER").get());
                userRepository.save(user);
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);


    }
    
}
