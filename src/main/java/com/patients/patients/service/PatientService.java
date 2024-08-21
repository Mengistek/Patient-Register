package com.patients.patients.service;

import com.patients.patients.Dto.PatientRequestDTO;
import com.patients.patients.Dto.PatientResponseDTO;
import com.patients.patients.execption.PatientNotFoundException;
import com.patients.patients.model.Patient;
import com.patients.patients.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements PatientImp{

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PatientService (PatientRepository patientRepository,ModelMapper modelMapper){
        this.modelMapper=modelMapper;
        this.patientRepository = patientRepository;
    }
    @Override
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patient -> modelMapper.map(patient,PatientResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found by Id: "+ id));
        return modelMapper.map(patient,PatientResponseDTO.class);
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRequestDTO.getContactPhoneNumber() == null) {
            throw new IllegalArgumentException("Contact phone number cannot be null");
        }

        Patient patient = modelMapper.map(patientRequestDTO, Patient.class);
        Patient savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientResponseDTO.class);
    }


    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        modelMapper.map(patientRequestDTO, existingPatient);
        Patient updatedPatient = patientRepository.save(existingPatient);
        return modelMapper.map(updatedPatient, PatientResponseDTO.class);
    }

    @Override
    public void deletePatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found with Id: "+id));
        patientRepository.delete(patient);
    }

    public List<Patient> getElderlyPatients(){
        LocalDate elderlyThreshold = LocalDate.now().minusYears(65);
        return patientRepository.findByDateOfBirthBeforeOrderByDateOfBirthAsc(elderlyThreshold);
    }
}
