package com.employee.management.config;

import com.employee.management.dao.gauthrepository.CredentialRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GoogleAuthenticatorConfig {
    private final CredentialRepository credentialRepository;
    @Bean
    public GoogleAuthenticator gAuth(){
        GoogleAuthenticator googleAuthenticator=new GoogleAuthenticator();
        googleAuthenticator.setCredentialRepository(credentialRepository);
        return googleAuthenticator;
    }
}
