package com.surver.app.backend.services.auth;

import com.surver.app.backend.util.AuthenticationRequest;
import com.surver.app.backend.util.AuthenticationResponse;
import com.surver.app.backend.util.RegisterRequest;
import com.surver.app.backend.entity.userentities.Role;

import com.surver.app.backend.entity.userentities.User;
import com.surver.app.backend.repository.userrepositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = repository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {

            user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);
        }
        String jwtToken = service.generateJwtToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = service.generateJwtToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
