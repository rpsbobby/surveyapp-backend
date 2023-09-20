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

import java.util.Date;

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
            return generateAuthenticationResponse(user);
        } else {
            AuthenticationRequest authenticationRequest = new AuthenticationRequest();
            authenticationRequest.setEmail(request.getEmail());
            authenticationRequest.setPassword(request.getPassword());
            return authenticate(authenticationRequest);

        }

    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        return generateAuthenticationResponse(user);
    }

    private AuthenticationResponse generateAuthenticationResponse(User user) {
        String jwtToken = service.generateJwtToken(user);
        Date expiration = service.extractExpiration(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).expiration(expiration).build();
    }
}
