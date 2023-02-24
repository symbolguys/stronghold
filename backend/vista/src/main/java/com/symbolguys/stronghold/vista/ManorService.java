package com.symbolguys.stronghold.vista;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.symbolguys.stronghold.vista.model.Manor;

@Service
public class ManorService {

    public List<Manor> getManors() {
        String uri = "http://manor:3001/get-manors";
        Manor[] manors = new RestTemplate().getForObject(uri, Manor[].class);
        return Arrays.asList(manors);
    }
}
