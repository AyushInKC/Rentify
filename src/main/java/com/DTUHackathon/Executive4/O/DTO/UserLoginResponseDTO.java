package com.DTUHackathon.Executive4.O.DTO;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String name;

    public UserLoginResponseDTO(String accessToken, String refreshToken,String name) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.name=name;
    }
}
