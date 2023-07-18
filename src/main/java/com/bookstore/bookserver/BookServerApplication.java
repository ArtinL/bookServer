package com.bookstore.bookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bookstore.bookserver", "com.bookstore.bookserver.api"})
public class BookServerApplication {

    public static void main(String[] args) {
        System.out.println("BookServerApplication.main() called");
        SpringApplication.run(BookServerApplication.class, args);
    }

}
