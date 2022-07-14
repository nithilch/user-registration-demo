package com.nithil.userregistration.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IpApiResponse {

    String status;

    String message;

    String country;

    String city;
}
