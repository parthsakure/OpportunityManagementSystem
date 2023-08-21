package com.atc.opportunity_management_system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.atc.opportunity_management_system.service.AuthService;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @GetMapping("authorize")
    public RedirectView authorize(@AuthenticationPrincipal OAuth2User user){
        Map<String,Object>userData = user.getAttributes();
        authService.authoriseUser(userData);
        // return userData;
        return new RedirectView("/api");
    }

}
