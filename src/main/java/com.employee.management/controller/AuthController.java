package com.employee.management.controller;

import com.employee.management.model.request.LoginRequest;
import com.employee.management.model.request.UserRequest;
import com.employee.management.model.response.AuthenticationResponse;
import com.employee.management.model.response.UserResponse;
import com.employee.management.security.JwtService;
import com.employee.management.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${root.url}/auth")
public class AuthController {

    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.saveUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

}
