package com.nithil.userregistration.controller;

import com.nithil.userregistration.model.UserRegistrationRequest;
import com.nithil.userregistration.model.UserRegistrationResponse;
import com.nithil.userregistration.service.IpApiService;
import com.nithil.userregistration.validation.UserRegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class UserRegistrationController {

    private final IpApiService ipApiService;

    public UserRegistrationController(IpApiService ipApiService) {
        this.ipApiService = ipApiService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public UserRegistrationResponse register(@RequestBody UserRegistrationRequest request) {
        UserRegistrationValidator.validate(request);

        UserRegistrationResponse response = UserRegistrationResponse.builder()
                .uuid(UUID.randomUUID())
                .city(ipApiService.getLocation(request.getIpAddress()).getCity())
                .welcomeMsg(request.getUsername()).build();


        return response;
    }
}
