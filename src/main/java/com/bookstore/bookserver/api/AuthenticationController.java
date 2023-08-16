package com.bookstore.bookserver.api;

import com.bookstore.bookserver.model.LoginResponseDTO;
import com.bookstore.bookserver.model.CredentialsDTO;
import com.bookstore.bookserver.model.UserDTO;
import com.bookstore.bookserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody CredentialsDTO body) {
        try {
            UserDTO user =  authenticationService.registerUser(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody CredentialsDTO body) {
        LoginResponseDTO user = authenticationService.loginUser(body.getUsername(), body.getPassword());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().build();
    }

}
