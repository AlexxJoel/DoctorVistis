package com.practice.DoctorVisits.controller.docs;

import com.practice.DoctorVisits.controller.dtos.CreateHistoryAppointmentReqDto;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.HistoryAppointmentEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "history appointment management")
public interface HistoryAppointmentControllerDocs {

    @Operation(summary = "Save a history record for an appointment")
    ResponseEntity<ApiResponse<Void>> saveHistoryAppointment(CreateHistoryAppointmentReqDto historyAppointment);

    @Operation(summary = "Get history by patient ID")
    ResponseEntity<ApiResponse<List<HistoryAppointmentEntity>>> getHistoryByPatientId(Long patientId);

    @Operation(summary = "Get history by doctor ID")
    ResponseEntity<ApiResponse<List<HistoryAppointmentEntity>>> getHistoryByDoctorId(Long doctorId);

    @Operation(summary = "Get all history appointments")
    ResponseEntity<ApiResponse<List<HistoryAppointmentEntity>>> getAllHistoryAppointments();


}
