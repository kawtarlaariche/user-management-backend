package com.laariche.usermanagement.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
