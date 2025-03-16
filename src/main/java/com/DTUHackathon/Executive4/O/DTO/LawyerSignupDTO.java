package com.DTUHackathon.Executive4.O.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LawyerSignupDTO {
    private String name;
    private String email;
    private String password;

}
