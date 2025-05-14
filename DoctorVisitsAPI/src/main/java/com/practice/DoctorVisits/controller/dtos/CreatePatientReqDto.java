package com.practice.DoctorVisits.controller.dtos;

import com.practice.DoctorVisits.model.entities.DoctorEntity;
import com.practice.DoctorVisits.model.entities.PatientEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.NonNull;

@Schema(description = "DTO for creating a new patient")
public class CreatePatientReqDto {
    @Schema(description = "Nombre del paciente", example = "Juan Pérez")
    @NonNull
    private String name;

    @Schema(description = "Edad del paciente", example = "30")
    @NonNull
    private Integer age;

    @Schema(description = "Historial médico del paciente", example = "Diabetes")
    @NonNull
    private String medicalHistory;

    public CreatePatientReqDto(@NonNull String name, @NonNull Integer age, @NonNull String medicalHistory) {
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getAge() {
        return age;
    }

    public void setAge(@NonNull Integer age) {
        this.age = age;
    }

    @NonNull
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(@NonNull String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public PatientEntity toEntity() {
        sanitize();
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(this.name);
        patientEntity.setAge(this.age);
        patientEntity.setMedicalHistory(this.medicalHistory);
        return patientEntity;
    }
    private void sanitize() {
        this.name = this.name.trim();
        this.medicalHistory = this.medicalHistory.trim();
    }
}
