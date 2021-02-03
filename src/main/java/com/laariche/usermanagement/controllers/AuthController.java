package com.laariche.usermanagement.controllers;

import com.laariche.usermanagement.entities.User;
import com.laariche.usermanagement.payloads.SignInRequest;
import com.laariche.usermanagement.payloads.SignUpRequest;
import com.laariche.usermanagement.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

}
