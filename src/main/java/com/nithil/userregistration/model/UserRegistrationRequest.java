package com.nithil.userregistration.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRequest {

    String username;

    String password;

    String ipAddress;
}
