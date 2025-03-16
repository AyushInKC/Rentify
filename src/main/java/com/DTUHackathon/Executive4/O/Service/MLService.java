package com.DTUHackathon.Executive4.O.Service;

import com.DTUHackathon.Executive4.O.DTO.MLRequestDTO;
import com.DTUHackathon.Executive4.O.DTO.MLResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MLService {

    private static final Logger logger = LoggerFactory.getLogger(MLService.class);
    private final RestTemplate restTemplate;
    private static final String API_URL = "https://rent-2-rdb4.onrender.com/chat";

    public MLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MLResponseDTO getChatResponse(MLRequestDTO request) {
        logger.info("Sending request to RentRite AI: {}", request.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MLRequestDTO> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<MLResponseDTO> response = restTemplate.exchange(
                    API_URL, HttpMethod.POST, entity, MLResponseDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Received successful response: {}", response.getBody().getReply());
                return response.getBody();
            } else {
                logger.warn("Received unexpected status code: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error while calling RentRite AI API", e);
        }

        MLResponseDTO errorResponse = new MLResponseDTO();
        errorResponse.setReply("Sorry, I had trouble processing your request.");
        return errorResponse;
    }
}
