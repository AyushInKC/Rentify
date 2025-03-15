package com.DTUHackathon.Executive4.O.Controller;

import com.DTUHackathon.Executive4.O.DTO.UserLoginDTO;
import com.DTUHackathon.Executive4.O.DTO.UserLoginResponseDTO;
import com.DTUHackathon.Executive4.O.DTO.UserSignUpDTO;
import com.DTUHackathon.Executive4.O.DTO.UserUpdateDTO;
import com.DTUHackathon.Executive4.O.Models.UserModel;
import com.DTUHackathon.Executive4.O.Repository.UserRepo;
import com.DTUHackathon.Executive4.O.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDTO userDTO) {
        return ResponseEntity.ok(userService.signUp(userDTO));
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email, @RequestBody UserUpdateDTO updatedUser) {
        return ResponseEntity.ok(userService.updateUserDetails(email, updatedUser));
    }

}
