package com.robinvandenhurk.gateway.example.serviceuser;

import com.robinvandenhurk.gateway.library.userinjection.UserInjectionLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(UserInjectionLoader.class)
public class ServiceUserApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

}
