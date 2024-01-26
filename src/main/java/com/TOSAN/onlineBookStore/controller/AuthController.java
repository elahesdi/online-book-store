package com.TOSAN.onlineBookStore.controller;

import com.TOSAN.onlineBookStore.dto.AuthRequestDto;
import com.TOSAN.onlineBookStore.dto.AuthResponseDto;
import com.TOSAN.onlineBookStore.dto.ResponseDto;
import com.TOSAN.onlineBookStore.exception.DuplicateUsernameException;
import com.TOSAN.onlineBookStore.exception.UserInfoNullException;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.security.jwt.JwtUtil;
import com.TOSAN.onlineBookStore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public ResponseEntity<ResponseDto> login(@RequestBody AuthRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return ResponseEntity.ok().body(new AuthResponseDto("you are successfully logged in", JwtUtil.generateToken(request.getUsername()))) ;
        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }

    }
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(@RequestBody AuthRequestDto request){
        try {
            service.addUser(new User(request.getUsername(), request.getPassword(), "CUSTOMER"));
            return ResponseEntity.ok().body(new ResponseDto("User Added Successfully"));
        }catch (DuplicateUsernameException | UserInfoNullException e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }
    }
}
