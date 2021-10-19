package com.citybank.service.impl;

import com.citybank.dto.LoginDto;
import com.citybank.dto.LoginResponseDto;
import com.citybank.dto.Response;
import com.citybank.filter.JwtTokenProvider;
import com.citybank.model.Role;
import com.citybank.model.User;
import com.citybank.repository.UserRepository;
import com.citybank.service.AuthService;
import com.citybank.util.ResponseBuilder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public Response login(LoginDto loginDto) {
        User user = userRepository.findByUsernameAndIsActiveTrue(loginDto.getUsername());
        if(user == null){
            return ResponseBuilder.getFailureResponse(HttpStatus.UNAUTHORIZED, "Invalid Username or password");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(jwtTokenProvider.generateToken(authentication));
            loginResponseDto.setName(user.getName());
            loginResponseDto.setUsername(user.getUsername());
            List<String> roles = new ArrayList<>();
            for(Role role:user.getRoles()) {
            	roles.add(role.getName());
            }
            loginResponseDto.setRoles(roles);
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Logged In Success", loginResponseDto);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Invalid Username or password");
    }
}
