package com.employee.management.service;

import com.employee.management.dao.entity.User;
import com.employee.management.model.AuthenticationResponse;
import com.employee.management.model.LoginRequest;
import com.employee.management.model.UserRequest;
import com.employee.management.model.UserResponse;

public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    AuthenticationResponse loginUser(LoginRequest loginRequest);
}
