package com.employee.management.service.impl;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dao.entity.User;
import com.employee.management.dao.repository.DepartmentRepository;
import com.employee.management.dao.repository.UserRepository;
import com.employee.management.mapper.EmployeeMapper;
import com.employee.management.mapper.UserMapper;
import com.employee.management.model.UserRequest;
import com.employee.management.model.UserResponse;
import com.employee.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse saveuser(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User user = UserMapper.INSTANCE.requestToEntity(userRequest);
        user = userRepository.save(user);
        logger.info("Created a new User with ID: {}", user.getId());
        return UserMapper.INSTANCE.entityToDto(user);
    }
}
