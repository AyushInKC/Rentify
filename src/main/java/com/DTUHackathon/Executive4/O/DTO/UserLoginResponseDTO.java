package com.DTUHackathon.Executive4.O.DTO;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String accessToken;
    private String refreshToken;

    public UserLoginResponseDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
