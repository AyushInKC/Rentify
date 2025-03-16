package com.DTUHackathon.Executive4.O.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "users")
@Entity
public class UserModel {
    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String age;
    private String gender;
    private String adhaarNum;
    private String panCardNum;
    @Lob
    private byte[] profilePhoto;

}
