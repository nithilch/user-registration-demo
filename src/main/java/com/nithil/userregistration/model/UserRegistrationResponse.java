package com.nithil.userregistration.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserRegistrationResponse {

    UUID uuid;

    String welcomeMsg;

    String city;
}
