package com.bookstore.bookserver.api;

import com.bookstore.bookserver.model.authdtos.LoginResponseDTO;
import com.bookstore.bookserver.model.authdtos.CredentialsDTO;
import com.bookstore.bookserver.model.authdtos.UserDTO;
import com.bookstore.bookserver.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
            UserDTO user =  authenticationService.registerUser(body.getEmail(), body.getUsername(), body.getPassword());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody CredentialsDTO body) {
        try {
            LoginResponseDTO user = authenticationService.loginUser(body.getEmail(), body.getUsername(), body.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
