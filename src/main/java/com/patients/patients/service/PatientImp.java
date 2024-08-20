package com.patients.patients.service;

import com.patients.patients.Dto.PatientRequestDTO;
import com.patients.patients.Dto.PatientResponseDTO;

import java.util.List;

public interface PatientImp {
    List<PatientResponseDTO> getAllPatients();
    PatientResponseDTO getPatientById(Long id);

    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

    PatientResponseDTO updatePatient(Long id , PatientRequestDTO patientDTO);

    void deletePatientById(Long id);
}
