package com.fastandfuriousrent.dto;

import com.fastandfuriousrent.enumeraciones.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;

    private UserRole userRole;

    private Long userId;
}
