package com.TOSAN.onlineBookStore.controller;

import com.TOSAN.onlineBookStore.dto.AuthRequestDto;
import com.TOSAN.onlineBookStore.dto.AuthResponseDto;
import com.TOSAN.onlineBookStore.exception.DuplicatedUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.model.Customer;
import com.TOSAN.onlineBookStore.security.jwt.JwtUtil;
import com.TOSAN.onlineBookStore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService service;

    public AuthController(AuthenticationManager authenticationManager, UserService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok().body(new AuthResponseDto("you are successfully logged in", JwtUtil.generateToken(request.getUsername()))) ;
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(new AuthResponseDto(e.getMessage()));
        }

    }
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto request){
        try {
            return ResponseEntity.ok().body(new AuthResponseDto(service.addUser(new Customer(request.getUsername(), request.getPassword(), "CUSTOMER"))));
        }catch (DuplicatedUsernameException | UserInfoNullException e){
            return ResponseEntity.badRequest().body(new AuthResponseDto(e.getMessage()));
        }
    }
}
