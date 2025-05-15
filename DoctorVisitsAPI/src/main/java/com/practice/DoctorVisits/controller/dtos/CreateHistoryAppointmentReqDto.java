package com.practice.DoctorVisits.controller.dtos;

import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import com.practice.DoctorVisits.model.entities.HistoryAppointmentEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Schema(description = "Request DTO for creating an appointment")
public class CreateHistoryAppointmentReqDto {

    @Schema(description = "Appointment ID", example = "1")
    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    @Schema(description = "information about the diagnosis of the patient", example = "Flu")
    @NotNull(message = "Diagnosis is required")
    private String diagnosis;

    @Schema(description = "information about the treatment of the patient", example = "Rest and hydration")
    @NotNull(message = "Treatment is required")
    private String treatment;

    public CreateHistoryAppointmentReqDto() {
    }

    public CreateHistoryAppointmentReqDto(@NotNull Long appointmentId, String diagnosis, String treatment) {
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    @NotNull
    public Long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(@NotNull Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    public HistoryAppointmentEntity toEntity() {
        sanitize();
        HistoryAppointmentEntity historyAppointmentEntity = new HistoryAppointmentEntity();
        historyAppointmentEntity.setAppointmentId(this.appointmentId);
        historyAppointmentEntity.setDiagnosis(this.diagnosis);
        historyAppointmentEntity.setPrescription(this.treatment);
        return historyAppointmentEntity;
    }
    private void sanitize() {
        this.diagnosis = this.diagnosis.trim();
        this.treatment = this.treatment.trim();
    }
}
