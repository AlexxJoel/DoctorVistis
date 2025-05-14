package com.practice.DoctorVisits.model.service;

import com.practice.DoctorVisits.core.ApiException;
import com.practice.DoctorVisits.model.entities.DoctorEntity;
import com.practice.DoctorVisits.model.repository.DoctorRepositoryIbatis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    //log
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepositoryIbatis doctorMapper;

    @Transactional
    public DoctorEntity registerDoctor(DoctorEntity doctor) {
        if (doctorMapper.existsByName(doctor.getName())) {
            logger.error("Doctor with name {} already exists", doctor.getName());
            throw new ApiException(
                    "Doctor with name " + doctor.getName() + " already exists",
                    HttpStatus.CONFLICT
            );
        }

        doctorMapper.insertDoctor(doctor);
        logger.info("Doctor registered successfully: {}", doctor);
        return doctor;
    }

    public DoctorEntity getDoctorById(Long id) {
        DoctorEntity doctor = doctorMapper.getDoctorById(id);
        if (doctor == null) {
            logger.error("Doctor with id {} not found", id);
            throw new ApiException(
                    "Doctor with id " + id + " not found",
                    HttpStatus.NOT_FOUND
            );
        }
        logger.info("Doctor found: {}", doctor);
        return doctor;
    }

    public List<DoctorEntity> getAllDoctors() {
        List<DoctorEntity> doctors = doctorMapper.getAllDoctors();
        if (doctors.isEmpty()) {
            logger.warn("No doctors found");
            throw new ApiException(
                    "No doctors found",
                    HttpStatus.NOT_FOUND
            );
        }
        logger.info("Doctors found: {}", doctors);
        return doctors;
    }
}
