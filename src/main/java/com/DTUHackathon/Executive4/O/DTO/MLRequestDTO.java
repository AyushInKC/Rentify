package com.DTUHackathon.Executive4.O.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MLRequestDTO {
  @NotBlank(message = "Query cannot be empty")
  private String query;
  @NotBlank(message = "Region cannot be empty")
  private String region;  // <---optional based on region
}
