package com.atc.opportunity_management_system.configuration;

import org.springframework.security.core.userdetails.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService detailsService(){
        UserDetails admin = User.builder()
                .username("Admin")
                .password("{noop}ADMIN")
                .authorities("ROLE_ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
        .authorizeHttpRequests(customizer->{
            customizer
            // .requestMatchers("/authorize/**").permitAll()
            // .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")

            // .requestMatchers(HttpMethod.POST, "/opportunity/**","/location/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")
            // .requestMatchers(HttpMethod.POST, "/company/**", "/country/**").hasAnyRole("ADMIN", "EMPLOYEE")
            // .requestMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN")

            // .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("USER", "EMPLOYEE", "ADMIN")
            // .requestMatchers(HttpMethod.PUT, "/opportunity/**").hasAnyRole("USER","EMPLOYEE","ADMIN")
            // .requestMatchers(HttpMethod.PUT, "/company/**","/location/**","/country/**").hasAnyRole("EMPLOYEE","ADMIN")

            // .requestMatchers(HttpMethod.DELETE, "/opportunity/**","/user/**","/company/**").hasRole("ADMIN")
            .anyRequest().permitAll();
            ;
        })
        .oauth2Login(cust->{
            cust
            .defaultSuccessUrl("/authorize",true);
        })
        .formLogin(Customizer.withDefaults())
        .logout(cust->cust
            .clearAuthentication(true)
            .logoutUrl("/logout")
        )
        .csrf(cust->cust.disable())
        .cors(cust->{
            cust.configurationSource(corsConfigurationSource());
        })
        .build();
    }


    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
    

}
