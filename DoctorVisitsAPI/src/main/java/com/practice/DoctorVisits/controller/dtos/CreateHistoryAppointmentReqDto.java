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

    @Schema(description = "Patient ID", example = "1")
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @Schema(description = "Doctor ID", example = "1")
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @Schema(description = "information about the diagnosis of the patient", example = "Flu")
    private String diagnosis;

    @Schema(description = "information about the treatment of the patient", example = "Rest and hydration")
    private String treatment;

    public CreateHistoryAppointmentReqDto() {
    }

    public CreateHistoryAppointmentReqDto(@NotNull Long appointmentId, @NotNull Long patientId, @NotNull Long doctorId, String diagnosis, String treatment) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public @NotNull(message = "Appointment ID is required") Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(@NotNull(message = "Appointment ID is required") Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public @NotNull(message = "Patient ID is required") Long getPatientId() {
        return patientId;
    }

    public void setPatientId(@NotNull(message = "Patient ID is required") Long patientId) {
        this.patientId = patientId;
    }

    public @NotNull(message = "Doctor ID is required") Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(@NotNull(message = "Doctor ID is required") Long doctorId) {
        this.doctorId = doctorId;
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
        historyAppointmentEntity.setDoctorId(this.doctorId);
        historyAppointmentEntity.setPatientId(this.patientId);
        historyAppointmentEntity.setDiagnosis(this.diagnosis);
        historyAppointmentEntity.setPrescription(this.treatment);
        return historyAppointmentEntity;
    }

    private void sanitize() {
        this.diagnosis = this.diagnosis.trim();
        this.treatment = this.treatment.trim();
    }
}
