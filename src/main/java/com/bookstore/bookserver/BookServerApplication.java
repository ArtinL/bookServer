package com.bookstore.bookserver;

import com.bookstore.bookserver.entities.ApplicationUser;
import com.bookstore.bookserver.entities.Role;
import com.bookstore.bookserver.repository.RoleRepository;
import com.bookstore.bookserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableMongoRepositories
@EnableWebSecurity
@ComponentScan(basePackages = {
        "com.bookstore.bookserver.api",
        "com.bookstore.bookserver.config",
        "com.bookstore.bookserver.entities",
        "com.bookstore.bookserver.model",
        "com.bookstore.bookserver.providers",
        "com.bookstore.bookserver.repository",
        "com.bookstore.bookserver.service",
        "com.bookstore.bookserver.util"
})
public class BookServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1, "admin", passwordEncoder.encode("password"), roles);
            userRepository.save(admin);
        };
    }

}
