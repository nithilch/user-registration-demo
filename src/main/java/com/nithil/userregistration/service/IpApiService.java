package com.nithil.userregistration.service;

import com.nithil.userregistration.model.IpApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IpApiService {

    @Value("${ip_api.url}")
    private String url;

    @Value("${ip_api.fields}")
    private String fields;
    @Autowired
    private RestTemplate template;

    public IpApiResponse getLocation(String ip){
        return template.getForObject(url + ip + fields, IpApiResponse.class);
    }

}
