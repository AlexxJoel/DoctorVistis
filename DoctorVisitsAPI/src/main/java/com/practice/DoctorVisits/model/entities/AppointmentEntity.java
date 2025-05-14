package com.practice.DoctorVisits.model.entities;

import java.time.LocalDate;

public class AppointmentEntity {
    private Long id;
    private LocalDate date;
    private String hour;
    private Long doctorId;
    private Long patientId;

    public AppointmentEntity() {
    }

    public AppointmentEntity(Long id, LocalDate date, String hour, Long doctorId, Long patientId) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}