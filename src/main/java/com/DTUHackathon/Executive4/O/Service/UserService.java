package com.DTUHackathon.Executive4.O.Service;

import com.DTUHackathon.Executive4.O.DTO.UserLoginDTO;
import com.DTUHackathon.Executive4.O.DTO.UserLoginResponseDTO;
import com.DTUHackathon.Executive4.O.DTO.UserSignUpDTO;
import com.DTUHackathon.Executive4.O.DTO.UserUpdateDTO;
import com.DTUHackathon.Executive4.O.Models.UserModel;
import com.DTUHackathon.Executive4.O.Repository.UserRepo;
import com.DTUHackathon.Executive4.O.Utils.JWTUtility;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtility jwtUtility;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, JWTUtility jwtUtility) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
    }

    public String signUp(UserSignUpDTO userDTO) {
        if (userRepo.findByEmail(userDTO.getEmail()).isPresent()) {
            return "User already exists!";
        }
        UserModel user = new UserModel();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepo.save(user);
        return "User registered successfully!";
    }

    public UserLoginResponseDTO login(UserLoginDTO loginRequest) {
        Optional<UserModel> userOptional = userRepo.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        UserModel user = userOptional.get();

        String accessToken = jwtUtility.generateAccessToken(String.valueOf(user));
        String refreshToken = jwtUtility.generateRefreshToken(String.valueOf(user));

        return new UserLoginResponseDTO(accessToken, refreshToken, user.getName());
    }

    public String updateUserDetails(String email, UserUpdateDTO updatedUser) {
        Optional<UserModel> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            user.setAge(updatedUser.getAge());
            user.setGender(updatedUser.getGender());
            user.setName(updatedUser.getName());
            user.setAdhaarNum(updatedUser.getAdhaarNum());
            user.setPanCardNum(updatedUser.getPanCardNum());
            userRepo.save(user);
            return "User details updated successfully!";
        }
        return "User not found!";
    }
}
