package com.DTUHackathon.Executive4.O.Repository;

import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.DTUHackathon.Executive4.O.Models.LawyerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawyerRepo extends MongoRepository<LawyerModel,String> {
    List<LawyerModel> findByNameContainingIgnoreCase(String name);
    List<LawyerModel> findByPhone(String phone);
    List<LawyerModel> findByDesignationContainingIgnoreCase(String designation);

    List<LawyerModel> findByQualificationContainingIgnoreCase(String qualification);
    List<LawyerModel> findByDistrictContainingIgnoreCase(String district);
    Optional<LawyerModel> findByEmail(String email);
}
