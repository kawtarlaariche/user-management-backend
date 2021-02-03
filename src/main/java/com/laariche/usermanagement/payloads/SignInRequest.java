package com.laariche.usermanagement.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
