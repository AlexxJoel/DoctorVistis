package com.practice.DoctorVisits.controller.dtos;

import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Schema(description = "Request DTO for creating an appointment")
public class CreateAppointmentReqDto {

    @Schema(description = "Date and time of the appointment", example = "2025-06-01T10:30")
    @NotNull(message = "Appointment date and time is required")
    private String appointmentDateTime;

    @Schema(description = "Doctor ID", example = "1")
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @Schema(description = "Patient ID", example = "1")
    @NotNull(message = "Patient ID is required")
    private Long patientId;

    public CreateAppointmentReqDto(@NotNull String appointmentDateTime, @NotNull Long doctorId, @NotNull Long patientId) {
        this.appointmentDateTime = appointmentDateTime;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    @NotNull
    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(@NotNull String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    @NotNull
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(@NotNull Long doctorId) {
        this.doctorId = doctorId;
    }

    @NotNull
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(@NotNull Long patientId) {
        this.patientId = patientId;
    }

    public AppointmentEntity toEntity() {
        sanitize();
        // Assuming appointmentDateTime is in ISO-8601 format
        // Convert it to LocalDateTime
        LocalDateTime appointmentDateTime = LocalDateTime.parse(this.appointmentDateTime);
        // Create a new AppointmentEntity and set its properties
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setDate(appointmentDateTime.toLocalDate());
        appointmentEntity.setHour(appointmentDateTime.toLocalTime().truncatedTo(ChronoUnit.MINUTES).toString());
        appointmentEntity.setDoctorId(this.doctorId);
        appointmentEntity.setPatientId(this.patientId);
        return appointmentEntity;
    }

    private void sanitize() {
        this.appointmentDateTime = this.appointmentDateTime.trim();
    }

    /**
     * Validates the appointment date and time format.
     *  format: YYYY-MM-DDTHH:MM
     *
     * @return true if the format is valid, false otherwise.
     */
    @Hidden
    public boolean isValidAppointmentDateTime() {
        String REGEX = "^(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2})$";
        return this.appointmentDateTime.matches(REGEX);
    }

    public void validate() {
        this.sanitize();
        if (this.appointmentDateTime.isEmpty()) {
            throw new ApiException("Appointment date and time cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (!isValidAppointmentDateTime()) {
            throw new ApiException("Invalid appointment date and time format. Expected format: YYYY-MM-DDTHH:MM", HttpStatus.BAD_REQUEST);
        }
    }


}
