package com.practice.DoctorVisits.controller;

import com.practice.DoctorVisits.controller.docs.PatientControllerDocs;
import com.practice.DoctorVisits.controller.dtos.CreatePatientReqDto;
import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.PatientEntity;
import com.practice.DoctorVisits.model.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController implements PatientControllerDocs {

    @Autowired
    private PatientService patientService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<PatientEntity>> registerPatient(
            @RequestBody @Validated CreatePatientReqDto patientReqDto
    ) {
        try {
            PatientEntity patient = patientService.registerPatient(patientReqDto.toEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(true, "Paciente registrado correctamente", patient)
            );
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(false, "Error inesperado: " + e.getMessage(), null)
            );
        }
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientEntity>> getPatientById(
            @PathVariable Long id
    ) {
        try {
            PatientEntity patient = patientService.getPatientById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Paciente encontrado", patient)
            );
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(false, "Error inesperado: " + e.getMessage(), null)
            );
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientEntity>>> getAllPatients() {
        try {
            List<PatientEntity> patients = patientService.getAllPatients();
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Lista de pacientes", patients)
            );
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse<>(false, "Error inesperado: " + e.getMessage(), null)
            );
        }
    }
}
