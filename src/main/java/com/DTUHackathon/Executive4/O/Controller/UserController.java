package com.DTUHackathon.Executive4.O.Controller;

import com.DTUHackathon.Executive4.O.DTO.UserLoginDTO;
import com.DTUHackathon.Executive4.O.DTO.UserLoginResponseDTO;
import com.DTUHackathon.Executive4.O.DTO.UserSignUpDTO;
import com.DTUHackathon.Executive4.O.DTO.UserUpdateDTO;
import com.DTUHackathon.Executive4.O.Models.UserModel;
import com.DTUHackathon.Executive4.O.Repository.UserRepo;
import com.DTUHackathon.Executive4.O.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserRepo userRepo;
    public UserController(UserService userService, UserRepo userRepo){
        this.userService=userService;
        this.userRepo=userRepo;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDTO userDTO) {
        return ResponseEntity.ok(userService.signUp(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginRequestDTO) {
        try {
            UserLoginResponseDTO response = userService.login(userLoginRequestDTO);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("accessToken", response.getAccessToken());
            responseBody.put("refreshToken", response.getRefreshToken());
            responseBody.put("email", response.getEmail());

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            logger.error("Login error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during login. Check server logs.");
        }
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserUpdateDTO updatedUser) {
        return ResponseEntity.ok(userService.updateUserDetails(email, updatedUser));
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email){
        try {
            Optional<UserModel> user = userRepo.findByEmail(email);

            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("uploadPhoto/{name}")
    public ResponseEntity<?> uploadPhoto(@PathVariable String name, @RequestParam("file") MultipartFile file) {
        String response = userService.uploadPhoto(name, file);

        if (response.equals("Lawyer not found!")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else if (response.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
