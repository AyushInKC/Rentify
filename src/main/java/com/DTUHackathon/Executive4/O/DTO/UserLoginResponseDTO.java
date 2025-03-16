package com.DTUHackathon.Executive4.O.DTO;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String email;

    public UserLoginResponseDTO(String accessToken, String refreshToken,String email) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email=email;
    }
}
