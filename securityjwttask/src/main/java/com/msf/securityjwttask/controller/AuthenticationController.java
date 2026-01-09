package com.msf.securityjwttask.controller;


import com.msf.securityjwttask.dtos.LoginUserDto;
import com.msf.securityjwttask.dtos.RegisterUserDto;
import com.msf.securityjwttask.entity.User;
import com.msf.securityjwttask.response.LoginResponse;
import com.msf.securityjwttask.services.AuthenticationService;
import com.msf.securityjwttask.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService
    ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
//   http://localhost:8005/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<User> register(
            @RequestBody RegisterUserDto registerUserDto
    ) {
        User registeredUser =
                authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
//   POST http://localhost:8005/auth/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginUserDto loginUserDto
    ) {
        User authenticatedUser =
                authenticationService.authenticate(loginUserDto);

        String jwtToken =
                jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
