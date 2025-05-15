package com.practice.DoctorVisits.model.repository;

import com.practice.DoctorVisits.model.entities.HistoryAppointmentEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HistoryAppointmentRepositoryIbatis {

    @Insert("INSERT INTO HISTORY_APPOINTMENTS (" +
            "ID, APPOINTMENT_ID, DIAGNOSIS, PRESCRIPTION) " +
            "VALUES (history_appointments_seq.NEXTVAL, #{appointmentId}, #{diagnosis}, #{prescription})")
    void insertHistory(HistoryAppointmentEntity history);

    @Select("SELECT * FROM HISTORY_APPOINTMENTS I INNER JOIN APPOINTMENTS A ON A.ID = I.APPOINTMENT_ID WHERE A.PATIENT_ID = #{patientId}")
    List<HistoryAppointmentEntity> findByPatientId(Long patientId);

    @Select(" SELECT * FROM HISTORY_APPOINTMENTS I INNER JOIN APPOINTMENTS A ON A.ID = I.APPOINTMENT_ID WHERE A.DOCTOR_ID = #{doctorId}")
    List<HistoryAppointmentEntity> findByDoctorId(Long doctorId);

    @Select("SELECT * FROM HISTORY_APPOINTMENTS")
    List<HistoryAppointmentEntity> findAll();
}
