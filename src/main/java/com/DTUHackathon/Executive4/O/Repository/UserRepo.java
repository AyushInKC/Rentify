package com.DTUHackathon.Executive4.O.Repository;

import com.DTUHackathon.Executive4.O.Models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<UserModel,String> {
    Optional<UserModel> findByEmail(String email);

    List<UserModel> findByNameContainingIgnoreCase(String name);
}
