package com.practice.DoctorVisits.model.repository;

import com.practice.DoctorVisits.model.entities.DoctorEntity;
import com.practice.DoctorVisits.model.entities.PatientEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PacientRepositoryIbatis {


    @Insert("INSERT INTO PATIENTS (ID, NAME, AGE, MEDICAL_HISTORY) VALUES (patients_seq.NEXTVAL, #{name}, #{age}, #{medicalHistory})")
    @SelectKey(
            statement = "SELECT patients_seq.CURRVAL FROM dual",
            keyProperty = "id",
            before = false,
            resultType = Long.class
    )
    void insertPatient(PatientEntity patient);


    @Select("SELECT * FROM PATIENTS WHERE ID = #{id}")
    PatientEntity getPatientById(Long id);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM PATIENTS WHERE NAME = #{name}")
    boolean existsByName(String name);


    @Select("SELECT * FROM PATIENTS")
    List<PatientEntity> getAllPatients();

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM PATIENTS WHERE ID = #{patientId}")
    boolean existsById(Long patientId);
}

