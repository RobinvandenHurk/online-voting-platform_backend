package com.robinvandenhurk.onlinevotingplatform.servicecandidate;

import com.robinvandenhurk.gateway.library.userinjection.LoadUserFromHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LoadUserFromHeaderFilter.class)
public class ServiceCandidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCandidateApplication.class, args);
	}

}
