package com.nithil.userregistration.validation;

import com.nithil.userregistration.error.CustomErrorException;
import com.nithil.userregistration.model.UserRegistrationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationValidatorTest {

    @Test
    void validate_isNull_false() {

        UserRegistrationRequest request = UserRegistrationRequest.builder()
                .username(null)
                .password(null).
                ipAddress(null).build();
        Exception exception = assertThrows(CustomErrorException.class, () -> {
            UserRegistrationValidator.validate(request);
        });

        assertTrue(exception.getMessage().contains("one of the properties is null or blank"));
    }

    @Test
    void validate_isBlank_false() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
                .username("")
                .password("").
                ipAddress("").build();
        Exception exception = assertThrows(CustomErrorException.class, () -> {
            UserRegistrationValidator.validate(request);
        });

        assertTrue(exception.getMessage().contains("one of the properties is null or blank"));
    }

    @Test
    void validate_password_invalid() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
                .username("test")
                .password("Fruits8)")
                .ipAddress("192.168.1.1").build();
        Exception exception = assertThrows(CustomErrorException.class, () -> {
            UserRegistrationValidator.validate(request);
        });

        assertTrue(exception.getMessage().contains("password validation failed"));
    }

    @Test
    void validate_password_valid() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
                .username("test")
                .password("Fruits8$").
                ipAddress("192.168.1.1").build();
        boolean result = UserRegistrationValidator.validate(request);

        assertTrue(result);
    }

    @Test
    void validate_ipAddress_invalid() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
                .username("test")
                .password("Fruits8$").
                ipAddress("192.168.1").build();
        Exception exception = assertThrows(CustomErrorException.class, () -> {
            UserRegistrationValidator.validate(request);
        });

        assertTrue(exception.getMessage().contains("ip address validation failed"));

    }

    @Test
    void validate_ipAddress_valid() {
        UserRegistrationRequest request = UserRegistrationRequest.builder()
                .username("nithil")
                .password("Fruits8$").
                ipAddress("192.168.1.1").build();
        boolean result = UserRegistrationValidator.validate(request);

        assertTrue(result);
    }
}