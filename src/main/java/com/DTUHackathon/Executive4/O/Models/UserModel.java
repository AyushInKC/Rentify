package com.DTUHackathon.Executive4.O.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "users")
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
}
