package com.atc.opportunity_management_system.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .authorizeHttpRequests(customizer->{
            customizer.anyRequest().authenticated();
        })
        .oauth2Login(cust->{
            cust
            .defaultSuccessUrl("/authorize",true);
        })
        // .formLogin(Customizer.withDefaults())
        .build();
    }



    // @Bean
    // public UserDetailsManager detailsManager(DataSource dataSource){
    //     JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
    //     return userDetailsManager;
    // }
}
