package com.atc.opportunity_management_system.configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


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
            .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")

            .requestMatchers(HttpMethod.POST, "/api/opportunities/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/api/locations/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/api/companies/**").hasAnyRole("ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/api/countries/**").hasAnyRole("ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("ADMIN")

            .requestMatchers(HttpMethod.DELETE, "/api/opportunities/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

            .requestMatchers(HttpMethod.PUT, "/api/opportunities/**").hasAnyRole("USER", "EMPLOYEE","ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("USER", "EMPLOYEE", "ADMIN")
            ;
        })
        .oauth2Login(cust->{
            cust
            .defaultSuccessUrl("/authorize",true);
        })
        .formLogin(Customizer.withDefaults())
        .logout(cust->cust.logoutSuccessUrl("/logout"))
        .csrf(cust->cust.disable())
        .build();   
    }

}
