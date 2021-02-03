package com.laariche.usermanagement.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String type;
    private String username;
    private String token;
}
