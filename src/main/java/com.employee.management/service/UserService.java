package com.employee.management.service;

import com.employee.management.model.UserRequest;
import com.employee.management.model.UserResponse;

public interface UserService {

    UserResponse saveuser(UserRequest userRequest);

}
