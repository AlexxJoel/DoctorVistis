package com.practice.DoctorVisits.controller.dtos;

import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.model.entities.DoctorEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Schema(description = "DTO for creating a new doctor")
public class CreateDoctorReqDto {

    @Schema(description = "Nombre del médico", example = "Carlos")
    @NonNull
    @NotBlank
    private String name;

    @Schema(description = "Especialidad del médico", example = "Cardiología")
    @NonNull
    @NotBlank
    private String specialty;

    public CreateDoctorReqDto(@NonNull String name, @NonNull String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(@NonNull String specialty) {
        this.specialty = specialty;
    }

    public DoctorEntity toEntity() {
        sanitize();
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setName(this.name);
        doctorEntity.setSpecialty(this.specialty);
        return doctorEntity;
    }

    private void sanitize() {
        this.name = this.name.trim();
        this.specialty = this.specialty.trim();
    }

    public void validate() {
        this.sanitize();
        if (this.name.isEmpty()) {
            throw new ApiException("Name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (this.specialty.isEmpty()) {
            throw new ApiException("Specialty cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
    }
}
