package com.DTUHackathon.Executive4.O.Controller;

import com.DTUHackathon.Executive4.O.Models.LawyerModel;
import com.DTUHackathon.Executive4.O.Repository.LawyerRepo;
import com.DTUHackathon.Executive4.O.Service.LawyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lawyers")
public class LawyerConroller {

     private final LawyerRepo lawyerRepo;

     private final LawyerService lawyerService;

    public LawyerConroller(LawyerRepo lawyerRepo, LawyerService lawyerService){
        this.lawyerRepo=lawyerRepo;
        this.lawyerService =lawyerService ;
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
