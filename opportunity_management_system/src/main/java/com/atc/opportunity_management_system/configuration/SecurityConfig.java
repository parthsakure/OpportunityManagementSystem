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
            // .anyRequest().permitAll()
            .requestMatchers("/authorize/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")

            .requestMatchers(HttpMethod.POST, "/opportunity/**","/location/**").hasAnyRole("USER","ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/company/**", "/country/**").hasAnyRole("ADMIN", "EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN")

            .requestMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("USER", "EMPLOYEE", "ADMIN")
            .requestMatchers(HttpMethod.PUT, "/opportunity/**").hasAnyRole("USER", "EMPLOYEE","ADMIN")

            .requestMatchers(HttpMethod.DELETE, "/opportunity/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
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
