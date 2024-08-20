package com.patients.patients.model;

import com.patients.patients.Enam.IsAnOutPatient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient_hcmc")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;


    @Column(unique = true)
    @NotBlank
    private String patientNumber;


    @Enumerated(EnumType.STRING)
    @NotNull
    private IsAnOutPatient isAnOutPatient;
    @Column(unique = true)
    private String fullName;

    @NotBlank
    @Column(unique = true)
    private String emailAddress;


    @NotBlank
    @Column(nullable = false)
    private String contactPhoneNumber;


    @NotBlank

    private LocalDate dateOfBirth;
}


