package com.robinvandenhurk.onlinevotingplatform.servicevotingform;

import com.robinvandenhurk.gateway.library.userinjection.LoadUserFromHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LoadUserFromHeaderFilter.class)
public class ServiceVotingFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVotingFormApplication.class, args);
	}

}
