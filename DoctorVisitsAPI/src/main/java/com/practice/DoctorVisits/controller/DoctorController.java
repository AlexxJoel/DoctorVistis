package com.practice.DoctorVisits.controller;

import com.practice.DoctorVisits.controller.docs.DoctorControllerDocs;
import com.practice.DoctorVisits.controller.dtos.CreateDoctorReqDto;
import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.DoctorEntity;
import com.practice.DoctorVisits.model.service.DoctorService;
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
@RequestMapping("/doctors")
public class DoctorController implements DoctorControllerDocs {

    @Autowired
    private DoctorService doctorService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<DoctorEntity>> registerDoctor(
            @RequestBody @Validated CreateDoctorReqDto doctorReqDto
    ) {
        try {
            DoctorEntity doctor = doctorService.registerDoctor(doctorReqDto.toEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>(true, "Médico registrado correctamente", doctor)
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
    public ResponseEntity<ApiResponse<DoctorEntity>> getDoctorById(
            @PathVariable  Long id
    ) {
        try {
            DoctorEntity doctor = doctorService.getDoctorById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Médico encontrado", doctor)
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
    public ResponseEntity<ApiResponse<List<DoctorEntity>>>getAllDoctors() {
        try {
            List<DoctorEntity> doctors = doctorService.getAllDoctors();
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Lista de médicos", doctors)
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
