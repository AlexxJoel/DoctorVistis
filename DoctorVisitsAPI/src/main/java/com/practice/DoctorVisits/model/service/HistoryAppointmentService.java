package com.practice.DoctorVisits.model.service;

import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.model.entities.HistoryAppointmentEntity;
import com.practice.DoctorVisits.model.repository.AppointmentRepositoryIbatis;
import com.practice.DoctorVisits.model.repository.DoctorRepositoryIbatis;
import com.practice.DoctorVisits.model.repository.HistoryAppointmentRepositoryIbatis;
import com.practice.DoctorVisits.model.repository.PacientRepositoryIbatis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryAppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryAppointmentService.class);

    @Autowired
    private AppointmentRepositoryIbatis appointmentRepository;
    @Autowired
    private PacientRepositoryIbatis patientRepository;
    @Autowired
    private DoctorRepositoryIbatis doctorRepository;
    @Autowired
    private HistoryAppointmentRepositoryIbatis historyAppointmentRepository;

    @Transactional
    public void saveHistoryAppointment(HistoryAppointmentEntity historyAppointment) {
        Long doctorId = historyAppointment.getDoctorId();
        Long patientId = historyAppointment.getPatientId();
        Long appointmentId = historyAppointment.getAppointmentId();

        // Check if the doctor and patient exist
        if (doctorRepository.existsById(doctorId)) {
            logger.error("Doctor with id {} not found", doctorId);
            throw new ApiException(
                    "Doctor with id " + doctorId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        // Check if the patient exists
        if (patientRepository.existsById(patientId)) {
            logger.error("Patient with id {} not found", patientId);
            throw new ApiException(
                    "Patient with id " + patientId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        // Check if the appointment exists
        if (appointmentRepository.existsById(appointmentId)) {
            logger.error("Appointment with id {} not found", appointmentId);
            throw new ApiException(
                    "Appointment with id " + appointmentId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        historyAppointmentRepository.insertHistory(historyAppointment);
        logger.info("History appointment saved successfully: {}", historyAppointment);
    }

    public List<HistoryAppointmentEntity> getHistoryByPatientId(Long patientId) {
        if (patientRepository.existsById(patientId)) {
            logger.error("Patient with id {} not found", patientId);
            throw new ApiException(
                    "Patient with id " + patientId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        List<HistoryAppointmentEntity> history = historyAppointmentRepository.findByPatientId(patientId);
        logger.info("History found for patient with id {}: {}", patientId, history);
        return history;
    }

    public List<HistoryAppointmentEntity> getHistoryByDoctorId(Long doctorId) {
        if (doctorRepository.existsById(doctorId)) {
            logger.error("Doctor with id {} not found", doctorId);
            throw new ApiException(
                    "Doctor with id " + doctorId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        List<HistoryAppointmentEntity> history = historyAppointmentRepository.findByDoctorId(doctorId);
        logger.info("History found for doctor with id {}: {}", doctorId, history);
        return history;
    }


}
