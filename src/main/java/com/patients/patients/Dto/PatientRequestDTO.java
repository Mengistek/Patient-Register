package com.patients.patients.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.patients.patients.Enam.IsAnOutPatient;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientRequestDTO {


    @JsonProperty("patientId")
    private Long patientId;

    @NotBlank(message = "Patient number cannot be blank")
    @JsonProperty("patientNumber")
    @Column(unique = true)
    private String patientNumber;

    @NotNull(message = "IsAnOutPatient cannot be null")
    @JsonProperty("isAnOutPatient")
    private IsAnOutPatient isAnOutPatient;

    @NotBlank(message = "Full name cannot be blank")
    @JsonProperty("fullName")
    @Column(unique = true)
    private String fullName;

    @NotBlank(message = "Email address cannot be blank")
    @JsonProperty("emailAddress")
    @Column(unique = true)
    private String emailAddress;

    @NotBlank(message ="Contact phone cannot be null")
    @JsonProperty("contactPhoneNumber")
    @Column(unique = true)
    private String contactPhoneNumber;

    @NotNull(message = "Date of birth cannot be null")
    @Column(unique = true)
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
}
