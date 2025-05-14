package com.practice.DoctorVisits.model.service;

import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.model.entities.AppointmentEntity;
import com.practice.DoctorVisits.model.entities.DoctorEntity;
import com.practice.DoctorVisits.model.entities.PatientEntity;
import com.practice.DoctorVisits.model.repository.AppointmentRepositoryIbatis;
import com.practice.DoctorVisits.model.repository.DoctorRepositoryIbatis;
import com.practice.DoctorVisits.model.repository.PacientRepositoryIbatis;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepositoryIbatis appointmentRepository;
    @Autowired
    private PacientRepositoryIbatis patientRepository;
    @Autowired
    private DoctorRepositoryIbatis doctorRepository;


    @Transactional
    public AppointmentEntity scheduleAppointment(AppointmentEntity appointment) {
        Long doctorId = appointment.getDoctorId();
        Long patientId = appointment.getPatientId();

        // Check if the doctor and patient exist
        DoctorEntity doctor = doctorRepository.getDoctorById(doctorId);
        if (doctor == null) {
            logger.error("Doctor with id {} not found", doctorId);
            throw new ApiException(
                    "Doctor with id " + doctorId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        // Check if the patient exists
        PatientEntity patient = patientRepository.getPatientById(patientId);
        if (patient == null) {
            logger.error("Patient with id {} not found", patientId);
            throw new ApiException(
                    "Patient with id " + patientId + " not found",
                    HttpStatus.NOT_FOUND
            );
        }

        LocalDateTime appointmentDateTime = LocalDateTime.parse(appointment.getDate() + "T" + appointment.getHour());
        LocalDate appointmentDate = appointmentDateTime.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (appointmentDate.isBefore(currentDate)) {
            logger.error("Appointment date {} cannot be in the past", appointmentDate);
            throw new ApiException(
                    "Appointment date cannot be in the past",
                    HttpStatus.BAD_REQUEST
            );
        }
        appointmentRepository.insertAppointment(appointment);
        logger.info("Appointment scheduled successfully: {}", appointment);
        return appointment;
    }


    public List<AppointmentEntity> getAllAppointments() {
        List<AppointmentEntity> appointments = appointmentRepository.getAll();
        if (appointments.isEmpty()) {
            logger.warn("No appointments found");
            throw new ApiException(
                    "No appointments found",
                    HttpStatus.NOT_FOUND
            );
        }
        return appointments;
    }

    public AppointmentEntity getAppointmentById(Long id) {
        AppointmentEntity appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            logger.error("Appointment with id {} not found", id);
            throw new ApiException(
                    "Appointment with id " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
        return appointment;
    }
}
