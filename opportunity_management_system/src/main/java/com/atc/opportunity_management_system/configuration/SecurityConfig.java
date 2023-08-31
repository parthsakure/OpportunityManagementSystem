package com.atc.opportunity_management_system.configuration;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@Configuration 

public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private JwtAuthFilter filter;

    // @Bean
    // public UserDetailsService detailsService(){
    //     UserDetails admin = User.builder()
    //             .username("Admin")
    //             .password("{noop}ADMIN")
    //             .authorities("ROLE_ADMIN")
    //             .build();
        
    //     return new InMemoryUserDetailsManager(admin);
    // }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
        .authorizeHttpRequests(customizer->{
            customizer
            
            .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")
            // .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")

            .requestMatchers(HttpMethod.POST, "/opportunity/**","/location/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/company/**", "/country/**").hasAnyRole("ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN")

            .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("USER", "EMPLOYEE", "ADMIN")
            .requestMatchers(HttpMethod.PUT, "/opportunity/**").hasAnyRole("USER","EMPLOYEE","ADMIN")
            .requestMatchers(HttpMethod.PUT, "/company/**","/location/**","/country/**").hasAnyRole("EMPLOYEE","ADMIN")

            .requestMatchers(HttpMethod.DELETE, "/opportunity/**","/user/**","/company/**").hasRole("ADMIN")
            .anyRequest().permitAll();
            ;
        })
        // .formLogin(Customizer.withDefaults())
        .logout(cust->cust
            .logoutUrl("/logout")
        )
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .csrf(cust->cust.disable())
        .cors(cust->{
            cust.configurationSource(corsConfigurationSource());
        })
        .build();
    }
    
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
