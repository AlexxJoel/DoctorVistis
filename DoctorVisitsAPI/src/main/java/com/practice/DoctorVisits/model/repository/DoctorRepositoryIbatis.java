package com.practice.DoctorVisits.model.repository;

import com.practice.DoctorVisits.model.entities.DoctorEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorRepositoryIbatis {


    @Insert("INSERT INTO DOCTORS (ID, NAME, SPECIALTY) VALUES (doctors_seq.NEXTVAL, #{name}, #{specialty})")
    @SelectKey(
            statement = "SELECT doctors_seq.CURRVAL FROM dual",
            keyProperty = "id",
            before = false,
            resultType = Long.class
    )
    void insertDoctor(DoctorEntity doctor);


    @Select("SELECT * FROM DOCTORS WHERE ID = #{id}")
    DoctorEntity getDoctorById(Long id);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM DOCTORS WHERE NAME = #{name}")
    boolean existsByName(String name);


    @Select("SELECT * FROM DOCTORS")
    List<DoctorEntity> getAllDoctors();

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM DOCTORS WHERE ID = #{doctorId}")
    boolean existsById(Long doctorId);
}

