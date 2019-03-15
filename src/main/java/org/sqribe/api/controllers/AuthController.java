package org.sqribe.api.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sqribe.api.models.User;
import org.sqribe.api.security.UserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/principal")
    public User getPrincipal() {
        UserDetailsImpl userDetails = (UserDetailsImpl)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }
}
