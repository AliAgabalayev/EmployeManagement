package com.employee.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;

    public static AuthenticationResponse of(String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}