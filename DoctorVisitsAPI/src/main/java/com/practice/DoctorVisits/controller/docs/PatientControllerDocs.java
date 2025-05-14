package com.practice.DoctorVisits.controller.docs;


import com.practice.DoctorVisits.controller.dtos.CreateDoctorReqDto;
import com.practice.DoctorVisits.controller.dtos.CreatePatientReqDto;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.DoctorEntity;
import com.practice.DoctorVisits.model.entities.PatientEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "patient management")
public interface PatientControllerDocs {
    @Operation(summary = "Register patient")
    public ResponseEntity<ApiResponse<PatientEntity>> registerPatient(CreatePatientReqDto patientReqDto);

    @Operation(summary = "Get patient by ID")
    public ResponseEntity<ApiResponse<PatientEntity>> getPatientById(Long id);

    @Operation(summary = "Get all patients")
    public ResponseEntity<ApiResponse<List<PatientEntity>>> getAllPatients();
}
