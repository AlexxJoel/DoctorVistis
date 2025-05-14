package com.practice.DoctorVisits.controller.docs;


import com.practice.DoctorVisits.controller.dtos.CreateDoctorReqDto;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.DoctorEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "doctor management")
public interface DoctorControllerDocs {
    @Operation(summary = "Register doctor")
    public ResponseEntity<ApiResponse<DoctorEntity>> registerDoctor(CreateDoctorReqDto doctorReqDto);

    @Operation(summary = "Get doctor by ID")
    public ResponseEntity<ApiResponse<DoctorEntity>> getDoctorById(Long id);

    @Operation(summary = "Get all doctors")
    public ResponseEntity<ApiResponse<List<DoctorEntity>>> getAllDoctors();
}
