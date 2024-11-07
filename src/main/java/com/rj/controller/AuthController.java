package com.rj.controller;

import com.rj.dto.UserDTO;
import com.rj.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController
{

    AuthenticationService authService;

    public AuthController(AuthenticationService AuthService)
    {
        this.authService = AuthService;
    }

    @GetMapping("/login")
    public String getLogin()
    {
        return "Login";
    }

    @PostMapping("/login")
    public UserDTO postLogin(@ModelAttribute UserDTO User)
    {
        return authService.loginUser(User.getUsername(), User.getPassword());
    }

    @GetMapping("/register")
    public String getRegister()
    {
        return "Register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute UserDTO user)
    {
        if (authService.registerUser(user.getUsername(), user.getPassword()) == null)
            return "Register";
        return "redirect:/login";
    }

}
