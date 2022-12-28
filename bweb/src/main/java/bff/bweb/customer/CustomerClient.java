package bff.bweb.customer;

import java.util.Map;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CustomerClient {
    

    //@PatchMapping("/{id}/")
    ClienteDTO partialUpdate(String authorizationHeader, long id, Map<String, Object> fields){
        String url =  "http://localhost:8000/api/cliente/"+id+"/";
        var restTemplate = new RestTemplate();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("X-HTTP-Method-Override", "PATCH");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(fields, headers);
        ClienteDTO response = null;
        try {
            response = restTemplate.execute (url,HttpMethod.PUT, entity, ClienteDTO.class);
        } catch (RestClientException exception) {
            System.out.println(exception.getLocalizedMessage());
            return null;
        }

        return response;
    
    }
}
