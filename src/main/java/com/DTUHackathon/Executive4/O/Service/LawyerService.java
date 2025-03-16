package com.DTUHackathon.Executive4.O.Service;

import com.DTUHackathon.Executive4.O.DTO.LawyerLoginRequestDTO;
import com.DTUHackathon.Executive4.O.DTO.LawyerLoginResponseDTO;
import com.DTUHackathon.Executive4.O.DTO.LawyerSignupDTO;
import com.DTUHackathon.Executive4.O.DTO.LawyerUpdateDTO;
import com.DTUHackathon.Executive4.O.Models.LawyerModel;
import com.DTUHackathon.Executive4.O.Repository.LawyerRepo;
import com.DTUHackathon.Executive4.O.Utils.JWTUtility;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LawyerService {


   private final LawyerRepo lawyerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtility jwtUtility;
    public LawyerService(LawyerRepo lawyerRepo,PasswordEncoder passwordEncoder,JWTUtility jwtUtility){
        this.lawyerRepo=lawyerRepo;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtility=jwtUtility;
    }


    public String signup(LawyerSignupDTO lawyerSignupDTO) {
        if (lawyerRepo.findByEmail(lawyerSignupDTO.getEmail()).isPresent()) {
            return "Lawyer already registered!";
        }
        LawyerModel lawyer = new LawyerModel();
        lawyer.setName(lawyerSignupDTO.getName());
        lawyer.setEmail(lawyerSignupDTO.getEmail());
        lawyer.setPassword(passwordEncoder.encode(lawyerSignupDTO.getPassword()));

        lawyerRepo.save(lawyer);
        return "Lawyer registered successfully!";
    }

    public LawyerLoginResponseDTO login(LawyerLoginRequestDTO lawyerLoginRequestDTO) {
        Optional<LawyerModel> lawyer = lawyerRepo.findByEmail(lawyerLoginRequestDTO.getEmail());

        if (lawyer.isPresent() && passwordEncoder.matches(lawyerLoginRequestDTO.getPassword(), lawyer.get().getPassword())) {
            String accessToken = jwtUtility.generateAccessToken(lawyerLoginRequestDTO.getEmail());
            String refreshToken = jwtUtility.generateRefreshToken(lawyerLoginRequestDTO.getEmail());
            return new LawyerLoginResponseDTO(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Invalid email or password!");
        }
    }

    public List<LawyerModel> findLawyerByName(String name) {
          return lawyerRepo.findByNameContainingIgnoreCase(name);
    }

    public List<LawyerModel> findLawyerByNumber(String number) {
        return lawyerRepo.findByNameContainingIgnoreCase(number);
    }

    public List<LawyerModel> listAll() {
        return lawyerRepo.findAll();
    }

    public List<LawyerModel> findLawyerByDesignation(String designation) {
        return lawyerRepo.findByDesignationContainingIgnoreCase(designation);
    }

    public List<LawyerModel> findLawyerByQualification(String qualification) {
        return lawyerRepo.findByQualificationContainingIgnoreCase(qualification);
    }

    public List<LawyerModel> findLawyerByDistrict(String district) {
        return lawyerRepo.findByDistrictContainingIgnoreCase(district);
    }

    public String updateLawyerDetails(String email, LawyerUpdateDTO lawyerUpdateDTO) {
        Optional<LawyerModel> lawyerOptional = lawyerRepo.findByEmail(email);

        if (lawyerOptional.isPresent()) {
            LawyerModel lawyer = lawyerOptional.get();

            if (lawyerUpdateDTO.getAge() != null) {
                lawyer.setAge(lawyerUpdateDTO.getAge());
            }
            if (lawyerUpdateDTO.getGender() != null) {
                lawyer.setGender(lawyerUpdateDTO.getGender());
            }
            if (lawyerUpdateDTO.getPhone() != null) {
                lawyer.setPhone(lawyerUpdateDTO.getPhone());
            }
            if (lawyerUpdateDTO.getDesignation() != null) {
                lawyer.setDesignation(lawyerUpdateDTO.getDesignation());
            }
            if (lawyerUpdateDTO.getQualification() != null) {
                lawyer.setQualification(lawyerUpdateDTO.getQualification());
            }
            if (lawyerUpdateDTO.getDistrict() != null) {
                lawyer.setDistrict(lawyerUpdateDTO.getDistrict());
            }

            lawyerRepo.save(lawyer);
            return "Lawyer details updated successfully!";
        } else {
            return "Lawyer with email " + email + " not found!";
        }
    }

    public String uploadPhoto(String name, MultipartFile file) {
        try {
            List<LawyerModel> lawyers = lawyerRepo.findByNameContainingIgnoreCase(name);

            if (lawyers.isEmpty()) {
                return "Lawyer not found!";
            }

            if (lawyers.size() > 1) {
                return "Multiple lawyers found with the same name. Please provide more details.";
            }

            LawyerModel lawyer = lawyers.get(0);
            lawyer.setProfilePhoto(file.getBytes());
            lawyerRepo.save(lawyer);

            return "Profile photo uploaded successfully!";
        } catch (IOException e) {
            return "Error uploading photo: " + e.getMessage();
        }
    }


}
