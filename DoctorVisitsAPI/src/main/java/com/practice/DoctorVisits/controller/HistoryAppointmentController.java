package com.practice.DoctorVisits.controller;

import com.practice.DoctorVisits.controller.docs.HistoryAppointmentControllerDocs;
import com.practice.DoctorVisits.controller.dtos.CreateHistoryAppointmentReqDto;
import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.core.ApiResponse;
import com.practice.DoctorVisits.model.entities.HistoryAppointmentEntity;
import com.practice.DoctorVisits.model.service.HistoryAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/history-appointments")
public class HistoryAppointmentController implements HistoryAppointmentControllerDocs {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HistoryAppointmentController.class);

    @Autowired
    private HistoryAppointmentService historyAppointmentService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveHistoryAppointment(@Valid @RequestBody  CreateHistoryAppointmentReqDto historyAppointment) {
        try {
            historyAppointment.validate();
             historyAppointmentService.saveHistoryAppointment(historyAppointment.toEntity());
            return ResponseEntity.ok(new ApiResponse<>(true, "History appointment saved successfully"));
        }catch (ApiException e){
            return ResponseEntity.status(e.getStatus()).body(new ApiResponse<>(false, e.getMessage()));
        }catch (Exception e){
            // Log the error (optional)
             logger.error("Error saving history appointment", e);
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "An error occurred while saving the history appointment" +e));
        }
    }

    @Override
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<List<HistoryAppointmentEntity>>> getHistoryByPatientId(@PathVariable Long patientId) {
        try {
            List<HistoryAppointmentEntity> historyAppointments = historyAppointmentService.getHistoryByPatientId(patientId);
            return ResponseEntity.ok(new ApiResponse<>(true, "History appointments retrieved successfully", historyAppointments));
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "An error occurred while retrieving the history appointments"));
        }
    }

    @Override
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<HistoryAppointmentEntity>>> getHistoryByDoctorId(@PathVariable Long doctorId) {
        try {
            List<HistoryAppointmentEntity> historyAppointments = historyAppointmentService.getHistoryByDoctorId(doctorId);
            return ResponseEntity.ok(new ApiResponse<>(true, "History appointments retrieved successfully", historyAppointments));
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "An error occurred while retrieving the history appointments"));
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<HistoryAppointmentEntity>>> getAllHistoryAppointments() {

        try {
            List<HistoryAppointmentEntity> historyAppointments = historyAppointmentService.getAllHistoryAppointments();
            return ResponseEntity.ok(new ApiResponse<>(true, "History appointments retrieved successfully", historyAppointments));
        } catch (ApiException e) {
            return ResponseEntity.status(e.getStatus()).body(new ApiResponse<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(false, "An error occurred while retrieving the history appointments"));
        }
    }
}
