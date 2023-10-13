package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class ArtworkService {

    Random random = new Random();
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> httpEntity = new HttpEntity<>("");
    private ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode jsonNode;
    private boolean hasImage = false;
    private String image;
    private int randomNumber;
    private String url;
    private ResponseEntity<String> response;

    @Value("{object.array.api.url}")
    private String objectArrayApiURL;


    public int getRandomArtwork() {
        while (!hasImage) {
            randomNumber = random.nextInt(471581);
            url = this.objectArrayApiURL + randomNumber;
            response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

            try {
                jsonNode = objectMapper.readTree(response.getBody());
                image = jsonNode.path("primaryImage").asText();
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            if (!image.equals("")) {
                hasImage = true;
            }
        }

        return 0;
    }


}
