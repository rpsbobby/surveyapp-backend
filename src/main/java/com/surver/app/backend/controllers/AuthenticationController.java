package com.surver.app.backend.controllers;


import com.surver.app.backend.services.auth.AuthenticationService;
import com.surver.app.backend.services.auth.JwtService;
import com.surver.app.backend.util.AuthenticationRequest;
import com.surver.app.backend.util.AuthenticationResponse;
import com.surver.app.backend.util.ErrorResponse;
import com.surver.app.backend.util.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private  final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
