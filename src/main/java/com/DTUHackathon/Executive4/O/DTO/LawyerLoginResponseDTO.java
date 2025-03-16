package com.DTUHackathon.Executive4.O.DTO;

import lombok.Data;

@Data

public class LawyerLoginResponseDTO {
    private String accessToken;
    private String refreshToken;

    public LawyerLoginResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
