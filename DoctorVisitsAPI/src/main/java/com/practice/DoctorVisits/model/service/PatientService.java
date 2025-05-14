package com.practice.DoctorVisits.model.service;

import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.model.entities.PatientEntity;
import com.practice.DoctorVisits.model.repository.PacientRepositoryIbatis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {

    //log
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PacientRepositoryIbatis patientMapper;

    @Transactional
    public PatientEntity registerPatient(PatientEntity patient) {
        if (patientMapper.existsByName(patient.getName())) {
            logger.error("Patient with name {} already exists", patient.getName());
            throw new ApiException(
                    "Patient with name " + patient.getName() + " already exists",
                    HttpStatus.CONFLICT
            );
        }

        patientMapper.insertPatient(patient);
        logger.info("Patient registered successfully: {}", patient);
        return patient;
    }

    public PatientEntity getPatientById(Long id) {
        PatientEntity patient = patientMapper.getPatientById(id);
        if (patient == null) {
            logger.error("Patient with id {} not found", id);
            throw new ApiException(
                    "Patient with id " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
        logger.info("Patient found: {}", patient);
        return patient;
    }

    public List<PatientEntity> getAllPatients() {
        List<PatientEntity> patients = patientMapper.getAllPatients();
        if (patients.isEmpty()) {
            logger.warn("No patients found");
            throw new ApiException(
                    "No patients found",
                    HttpStatus.NOT_FOUND
            );
        }
        logger.info("Patients found: {}", patients);
        return patients;
    }
}
