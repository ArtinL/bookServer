package com.bookstore.bookserver.service;

import com.bookstore.bookserver.entities.ApplicationUser;
import com.bookstore.bookserver.entities.Role;
import com.bookstore.bookserver.model.authdtos.LoginResponseDTO;
import com.bookstore.bookserver.model.authdtos.UserDTO;
import com.bookstore.bookserver.repository.RoleRepository;
import com.bookstore.bookserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public UserDTO registerUser(String email, String username, String password) {
        if (username == null || password == null || email == null ||
                username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").
                orElseThrow(() -> new RuntimeException("User role not found"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser user = userRepository.save(
                new ApplicationUser(0, email, username, encodedPassword, authorities)
        );

        return new UserDTO(user);
    }

    public LoginResponseDTO loginUser(String email, String username, String password) throws Exception {
        if (username == null || email == null || password == null )
            throw new IllegalArgumentException("Username and password cannot be empty");

        if (username.isEmpty())
            username = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"))
                    .getUsername();


        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String token = tokenService.generateJwt(auth);

        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new LoginResponseDTO(new UserDTO(user), token);


    }


}
