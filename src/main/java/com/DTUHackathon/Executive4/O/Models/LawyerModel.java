package com.DTUHackathon.Executive4.O.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "lawyers")
@Data
@Entity
public class LawyerModel {
    @Id
    private String id;
    private String name;
    private String phone;
    private String designation;
    private String qualification;
    private String district;
    private String email;
    private String gender;
    private String password;
   private String age;
    @Lob
    private byte[] profilePhoto;

}
