package com.laariche.usermanagement.services;

import com.laariche.usermanagement.entities.User;
import com.laariche.usermanagement.exception.ApiRequestException;
import com.laariche.usermanagement.payloads.JwtResponse;
import com.laariche.usermanagement.payloads.SignInRequest;
import com.laariche.usermanagement.payloads.SignUpRequest;
import com.laariche.usermanagement.repositories.IUserRepository;
import com.laariche.usermanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;


    public ResponseEntity signIn(SignInRequest signInRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getEmail(),
                            signInRequest.getPassword()
                    ));

            final User user = iUserRepository.findByEmail(signInRequest.getEmail()).get();

            return ResponseEntity.ok(
                    new JwtResponse(user.getId(), "Bearer", user.getEmail(), jwtUtil.generateToken(user.getEmail()))
            );

        } catch (Exception ex) {
            throw new ApiRequestException("Invalid password or username");
        }
    }

    public ResponseEntity signUp(SignUpRequest signUpRequest) {
        if (iUserRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ApiRequestException("Username already exists!");
        }

        if (iUserRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ApiRequestException("Email already exists!");
        }

        final User user = new User(
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );
        iUserRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
