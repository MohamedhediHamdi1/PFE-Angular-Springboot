package Cryptoo.com.example.Cryptoo.services.impl;

import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class TwilioMessageSender {
    private final String ACCOUNT_SID = "AC63a08f90266a35b73af7ebf9713198d5";
    private final String AUTH_TOKEN = "d114766c261608e6ff570dd29ed84cc2";
    private final String MESSAGE_ENDPOINT = "https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json";
    private final RestTemplate restTemplate;
    public TwilioMessageSender() {
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new BasicAuthenticationInterceptor(ACCOUNT_SID, AUTH_TOKEN)));
    }
    public void sendMessage(String toNumber, String fromNumber, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("To", toNumber);
        body.add("From", fromNumber);
        body.add("Body", message);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(MESSAGE_ENDPOINT, request, String.class);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to send message: " + response.getBody());
        }
    }
}
