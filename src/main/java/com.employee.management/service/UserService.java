package com.employee.management.service;

import com.employee.management.model.response.AuthenticationResponse;
import com.employee.management.model.request.LoginRequest;
import com.employee.management.model.request.UserRequest;
import com.employee.management.model.response.UserResponse;

public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    AuthenticationResponse loginUser(LoginRequest loginRequest);
}
