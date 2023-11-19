package com.employee.management.service.impl;

import com.employee.management.dao.entity.Employee;
import com.employee.management.dao.entity.User;
import com.employee.management.dao.repository.DepartmentRepository;
import com.employee.management.dao.repository.UserRepository;
import com.employee.management.mapper.EmployeeMapper;
import com.employee.management.mapper.UserMapper;
import com.employee.management.model.AuthenticationResponse;
import com.employee.management.model.LoginRequest;
import com.employee.management.model.UserRequest;
import com.employee.management.model.UserResponse;
import com.employee.management.service.UserService;
import com.employee.management.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User user = UserMapper.INSTANCE.requestToEntity(userRequest);
        user = userRepository.save(user);
        logger.info("Created a new User with ID: {}", user.getId());
        return UserMapper.INSTANCE.entityToDto(user);
    }

    @Override
    public AuthenticationResponse loginUser(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        User user=userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for username " + loginRequest.getUsername()));

        String token= jwtService.generateToken(user);

        logger.info("ActionLog.login.end loginrequest: {}", loginRequest);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }


}
