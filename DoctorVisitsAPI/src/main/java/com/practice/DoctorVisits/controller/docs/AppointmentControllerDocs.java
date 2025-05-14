package com.practice.DoctorVisits.controller.docs;

import com.practice.DoctorVisits.controller.dtos.CreateAppointmentReqDto;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "appointment management")
public interface AppointmentControllerDocs {

    @Operation(summary = "Schedule an appointment")
    ResponseEntity<ApiResponse<AppointmentEntity>> scheduleAppointment(CreateAppointmentReqDto appointmentReqDto);

    @Operation(summary = "Get appointment by ID")
    ResponseEntity<ApiResponse<AppointmentEntity>> getAppointmentById(Long id);

    @Operation(summary = "Get all appointments")
    ResponseEntity<ApiResponse<List<AppointmentEntity>>> getAllAppointments();
}