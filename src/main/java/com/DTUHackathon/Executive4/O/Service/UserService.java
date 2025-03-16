package com.DTUHackathon.Executive4.O.Service;

import com.DTUHackathon.Executive4.O.DTO.UserLoginDTO;
import com.DTUHackathon.Executive4.O.DTO.UserLoginResponseDTO;
import com.DTUHackathon.Executive4.O.DTO.UserSignUpDTO;
import com.DTUHackathon.Executive4.O.DTO.UserUpdateDTO;
import com.DTUHackathon.Executive4.O.Models.UserModel;
import com.DTUHackathon.Executive4.O.Repository.UserRepo;
import com.DTUHackathon.Executive4.O.Utils.JWTUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
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
        try {
            Optional<UserModel> userOptional = userRepo.findByEmail(loginRequest.getEmail());

            if (userOptional.isEmpty()) {
                logger.error("User not found with email: {}", loginRequest.getEmail());
                throw new RuntimeException("Invalid email or password");
            }

            UserModel user = userOptional.get();
            logger.info("User found: {}", user.getEmail());

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                logger.error("Password mismatch for user: {}", loginRequest.getEmail());
                throw new RuntimeException("Invalid email or password");
            }

            logger.info("Generating access token...");
            String accessToken = jwtUtility.generateAccessToken(user.getEmail());  // Ensure proper token generation
            String refreshToken = jwtUtility.generateRefreshToken(user.getEmail());

            logger.info("Login successful for user: {}", user.getEmail());
            return new UserLoginResponseDTO(accessToken, refreshToken, user.getEmail());

        } catch (Exception e) {
            logger.error("Unexpected error during login: ", e);
            throw new RuntimeException("An error occurred during login");
        }
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

    public String uploadPhoto(String name, MultipartFile file) {
        try {
            List<UserModel> users = userRepo.findByNameContainingIgnoreCase(name);

            if (users.isEmpty()) {
                return "Lawyer not found!";
            }

            UserModel user = users.get(0);
            user.setProfilePhoto(file.getBytes());
            userRepo.save(user);

            return "Profile photo uploaded successfully!";
        } catch (IOException e) {
            return "Error uploading photo: " + e.getMessage();
        }
    }
}
