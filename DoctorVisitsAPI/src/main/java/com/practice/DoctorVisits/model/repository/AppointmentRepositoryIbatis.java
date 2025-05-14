package com.practice.DoctorVisits.model.repository;


import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppointmentRepositoryIbatis {

    @Insert("INSERT INTO APPOINTMENTS (ID, APP_DATE, APP_TIME, DOCTOR_ID, PATIENT_ID) " +
            "VALUES (appointments_seq.NEXTVAL, #{date}, #{hour}, #{doctorId}, #{patientId})")
    @SelectKey(statement = "SELECT appointments_seq.CURRVAL FROM dual",
            keyProperty = "id",
            before = false,
            resultType = Long.class)
    void insertAppointment(AppointmentEntity appointment);


    @Select("SELECT ID, APP_DATE, APP_TIME, DOCTOR_ID, PATIENT_ID FROM APPOINTMENTS WHERE ID = #{id}")
    AppointmentEntity findById(Long id);

    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM APPOINTMENTS WHERE ID = #{appointmentId}")
    boolean existsById(Long appointmentId);

    @Select("SELECT ID, APP_DATE, APP_TIME, DOCTOR_ID, PATIENT_ID FROM APPOINTMENTS")
    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "date", column = "APP_DATE"),
            @Result(property = "hour", column = "APP_TIME"),
            @Result(property = "doctorId", column = "DOCTOR_ID"),
            @Result(property = "patientId", column = "PATIENT_ID")
    })
    List<AppointmentEntity> getAll();

}