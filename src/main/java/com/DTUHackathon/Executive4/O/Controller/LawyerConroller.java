package com.DTUHackathon.Executive4.O.Controller;

import com.DTUHackathon.Executive4.O.DTO.LawyerLoginRequestDTO;
import com.DTUHackathon.Executive4.O.DTO.LawyerLoginResponseDTO;
import com.DTUHackathon.Executive4.O.DTO.LawyerUpdateDTO;
import com.DTUHackathon.Executive4.O.DTO.LawyerSignupDTO;
import com.DTUHackathon.Executive4.O.Models.LawyerModel;
import com.DTUHackathon.Executive4.O.Repository.LawyerRepo;
import com.DTUHackathon.Executive4.O.Service.LawyerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/lawyers")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://rentrite-live.vercel.app"
}, allowCredentials = "true")
public class LawyerConroller {

     private final LawyerRepo lawyerRepo;

     private final LawyerService lawyerService;

    public LawyerConroller(LawyerRepo lawyerRepo, LawyerService lawyerService){
        this.lawyerRepo=lawyerRepo;
        this.lawyerService =lawyerService ;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody LawyerSignupDTO lawyerSignupDTO){
        String response = lawyerService.signup(lawyerSignupDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LawyerLoginRequestDTO lawyerLoginRequestDTO){
        LawyerLoginResponseDTO response = lawyerService.login(lawyerLoginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/uploadPhoto/{name}")
    public ResponseEntity<?> uploadPhoto(@PathVariable String name, @RequestParam("file") MultipartFile file) {
        String response = lawyerService.uploadPhoto(name, file);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateDetails(@PathVariable String email, @RequestBody LawyerUpdateDTO lawyerUpdateDTO) {
        String response = lawyerService.updateLawyerDetails(email, lawyerUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-name")
    public ResponseEntity<?> findLawyerByName(@RequestParam String name){
        List<LawyerModel> lawyers=lawyerService.findLawyerByName(name);
        return lawyers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lawyers);
    }

    @GetMapping("/by-number")
    public ResponseEntity<?> findLawyerByNumber(@RequestParam String number){
        List<LawyerModel> lawyers=lawyerService.findLawyerByNumber(number);
        return lawyers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lawyers);
    }

    @GetMapping("/by-designation")
    public ResponseEntity<?> findLawyerByDesignation(@RequestParam String designation){
        List<LawyerModel> lawyers=lawyerService.findLawyerByDesignation(designation);
        return lawyers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lawyers);
    }

    @GetMapping("/by-qualification")
    public ResponseEntity<?> findLawyerByQualification(@RequestParam String qualification){
        List<LawyerModel> lawyers=lawyerService.findLawyerByQualification(qualification);
        return lawyers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lawyers);
    }
    @GetMapping("/by-district")
    public ResponseEntity<?> findLawyerByDistrict(@RequestParam String district){
        List<LawyerModel> lawyers=lawyerService.findLawyerByDistrict(district);
        return lawyers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lawyers);
    }
    @GetMapping("/list-all")
    public ResponseEntity<?> listAll(){
        List<LawyerModel> lawyers=lawyerService.listAll();
        return lawyers.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(lawyers);
    }

}
