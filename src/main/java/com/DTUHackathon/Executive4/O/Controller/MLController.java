package com.DTUHackathon.Executive4.O.Controller;

import com.DTUHackathon.Executive4.O.DTO.MLRequestDTO;
import com.DTUHackathon.Executive4.O.DTO.MLResponseDTO;
import com.DTUHackathon.Executive4.O.Service.MLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class MLController {

    private final MLService mlService;

    public MLController(MLService mlService){
        this.mlService = mlService;
    }

    @PostMapping
    public ResponseEntity<MLResponseDTO> chat(@RequestBody MLRequestDTO message) {
        MLResponseDTO response = mlService.getChatResponse(message);
        return ResponseEntity.ok(response);
    }

}
