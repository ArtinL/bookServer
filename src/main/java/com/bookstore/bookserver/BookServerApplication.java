package com.bookstore.bookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bookstore.bookserver.api", "com.bookstore.bookserver.providers", "com.bookstore.bookserver.service",
        "com.bookstore.bookserver.model", "com.bookstore.bookserver.repository"})
public class BookServerApplication {

    public static void main(String[] args) {
        //System.out.println("BookServerApplication.main() called");
        SpringApplication.run(BookServerApplication.class, args);
    }

}
