package com.practice.DoctorVisits.model.entities;


public class HistoryAppointmentEntity {
    private Long id;
    private Long appointmentId;
    private String diagnosis;
    private String prescription;

    public HistoryAppointmentEntity() {
    }

    public HistoryAppointmentEntity(Long id, Long appointmentId, String diagnosis, String prescription) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }



    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

}