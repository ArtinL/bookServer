package com.bookstore.bookserver.service;

import com.bookstore.bookserver.entities.ApplicationUser;
import com.bookstore.bookserver.entities.Role;
import com.bookstore.bookserver.model.LoginResponseDTO;
import com.bookstore.bookserver.repository.RoleRepository;
import com.bookstore.bookserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    public ApplicationUser registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").
                orElseThrow(() -> new RuntimeException("User role not found"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser newUser = new ApplicationUser(0, username, encodedPassword, authorities);

        return userRepository.save(newUser);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found")), token);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new LoginResponseDTO(null, "");
        }

    }


}