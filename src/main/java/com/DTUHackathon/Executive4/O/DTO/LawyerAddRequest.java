package com.DTUHackathon.Executive4.O.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LawyerAddRequest {
    private String name;
    private String phone;
    private String designation;
    private String qualification;
    private String district;
}
