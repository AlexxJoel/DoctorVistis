package com.practice.DoctorVisits.model.repository;


import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

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


    @Select("SELECT ID, DATE, HOUR, DOCTOR_ID, PATIENT_ID FROM APPOINTMENTS WHERE ID = #{id}")
    AppointmentEntity findById(Long id);
}