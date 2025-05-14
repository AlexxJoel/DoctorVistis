package com.practice.DoctorVisits.controller;

import com.practice.DoctorVisits.controller.docs.AppointmentControllerDocs;
import com.practice.DoctorVisits.controller.dtos.CreateAppointmentReqDto;
import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import com.practice.DoctorVisits.model.service.AppointmentService;
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
@RequestMapping("/appointments")
public class AppointmentController implements AppointmentControllerDocs {

    @Autowired
    private AppointmentService appointmentService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentEntity>> scheduleAppointment(
            @Validated @RequestBody CreateAppointmentReqDto appointmentReqDto) {
        try {
            if (!appointmentReqDto.isValidAppointmentDateTime()) {
                throw new ApiException(
                        "The appointment date and time is not valid - date format is YYYY-MM-DDTHH:MM",
                        HttpStatus.BAD_REQUEST);
            }

            AppointmentEntity appointment = appointmentService.scheduleAppointment(appointmentReqDto.toEntity());
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Cita programada correctamente", appointment)
            );
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(false, "Error inesperado: " + e.getMessage(), null)
            );
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentEntity>> getAppointmentById(@PathVariable Long id) {
        try {
            AppointmentEntity appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Cita encontrada", appointment)
            );
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(false, "Error inesperado: " + e.getMessage(), null)
            );
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentEntity>>> getAllAppointments() {
        try {
            List<AppointmentEntity> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Citas encontradas", appointments)
            );
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    new ApiResponse<>(false, "Error inesperado: " + e.getMessage(), null)
            );
        }
    }
}
