package com.bookstore.bookserver.api;

import com.bookstore.bookserver.entities.ApplicationUser;
import com.bookstore.bookserver.model.LoginResponseDTO;
import com.bookstore.bookserver.model.RegistrationDTO;
import com.bookstore.bookserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        ApplicationUser user =  authenticationService.registerUser(body.getUsername(), body.getPassword());
        System.out.println(user);
        return user;
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

}
