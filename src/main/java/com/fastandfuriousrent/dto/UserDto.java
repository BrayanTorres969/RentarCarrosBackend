package com.fastandfuriousrent.dto;

import com.fastandfuriousrent.enumeraciones.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole userRole;
}
