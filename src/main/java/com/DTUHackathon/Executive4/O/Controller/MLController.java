package com.DTUHackathon.Executive4.O.Controller;

import com.DTUHackathon.Executive4.O.DTO.MLRequestDTO;
import com.DTUHackathon.Executive4.O.DTO.MLResponseDTO;
import com.DTUHackathon.Executive4.O.Service.MLService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class MLController {

    private final MLService mlService;

    private static final Logger logger = LoggerFactory.getLogger(MLController.class);
    public MLController(MLService mlService){
        this.mlService = mlService;
    }
    @PostMapping
    public ResponseEntity<MLResponseDTO> chat(@Valid @RequestBody MLRequestDTO message) {
        logger.info("Received request: {}", message);

        MLResponseDTO response = mlService.getChatResponse(message);

        logger.info("Generated response: {}", response);
        return ResponseEntity.ok(response);
    }
}
