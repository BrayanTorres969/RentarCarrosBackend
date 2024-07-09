package com.fastandfuriousrent.services.auth;

import com.fastandfuriousrent.dto.SignupRequest;
import com.fastandfuriousrent.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);
    boolean hasCustomerWithEmail(String email);
}
