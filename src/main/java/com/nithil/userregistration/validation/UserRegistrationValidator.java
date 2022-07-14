package com.nithil.userregistration.validation;

import com.nithil.userregistration.error.CustomErrorException;
import com.nithil.userregistration.model.UserRegistrationRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

public class UserRegistrationValidator {

    /*** Regex Rules:
     At least one upper case English letter, (?=.*?[A-Z])
     At least one lower case English letter, (?=.*?[a-z])
     At least one digit, (?=.*?[0-9])
     At least one special character, (?=.*?[_#$%.])
     Minimum eight in length .{8,} (with the anchors) ***/
    private static final String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[_#$%.]).{8,}$";

    public static boolean validate(UserRegistrationRequest request) {
        if (StringUtils.isBlank(request.getUsername()) || StringUtils.isBlank(request.getPassword()) || StringUtils.isBlank(request.getIpAddress()))
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "one of the properties is null or blank");

        if (!Pattern.matches(passwordRegex, request.getPassword()))
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "password validation failed");

        InetAddressValidator ipValidator = InetAddressValidator.getInstance();
        if(!ipValidator.isValidInet4Address(request.getIpAddress()))
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ip address validation failed");

        return true;
    }
}
