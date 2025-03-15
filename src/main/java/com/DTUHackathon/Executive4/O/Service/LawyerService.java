package com.DTUHackathon.Executive4.O.Service;


import com.DTUHackathon.Executive4.O.Models.LawyerModel;
import com.DTUHackathon.Executive4.O.Repository.LawyerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LawyerService {


   private final LawyerRepo lawyerRepo;

    public LawyerService(LawyerRepo lawyerRepo){
        this.lawyerRepo=lawyerRepo;
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

}
