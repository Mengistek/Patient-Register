package com.patients.patients.Dto;

import com.patients.patients.Enam.IsAnOutPatient;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientResponseDTO {
    private Long patientId;
    private String patientNumber;
    private IsAnOutPatient isAnOutPatient;
    private String fullName;
    private String emailAddress;
    private String contactPhoneNumber;
    private LocalDate dateOfBirth;
}
