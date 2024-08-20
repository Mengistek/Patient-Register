package com.patients.patients.controller;

import com.patients.patients.Dto.PatientRequestDTO;
import com.patients.patients.Dto.PatientResponseDTO;
import com.patients.patients.model.Patient;
import com.patients.patients.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import java.util.List;
//@Validated
//@RestController
//@RequestMapping("/api/v1/patients")
//public class PatientController {
//    @Autowired
//    private PatientService patientService;
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<PatientResponseDTO> getAllPatients(){
//        return patientService.getAllPatients();
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public PatientResponseDTO getPatientById(@PathVariable Long id){
//        return patientService.getPatientById(id);
//    }
//
//    @PostMapping("/add-patient")
//    @ResponseStatus(HttpStatus.CREATED)
//    public PatientResponseDTO createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
//        return  patientService.createPatient(patientRequestDTO);
//
//    }
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
//    public PatientResponseDTO update(@PathVariable Long id,@RequestBody PatientRequestDTO patientRequestDTO){
//        return patientService.updatePatient(id,patientRequestDTO);
//    }
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePatient(@PathVariable Long id){
//        patientService.deletePatientById(id);
//    }
//}


 //       ******** UI *********


//  thymeleaf platform



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "/patient/patients"; // This will render the patients.html template
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new PatientRequestDTO());
        return "patient/register_patient";
    }

    @PostMapping
    public String registerPatient(@ModelAttribute("patient") PatientRequestDTO patientRequestDTO) {
        patientService.createPatient(patientRequestDTO);
        return "redirect:/patients"; // Redirects to the patients list page
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        PatientResponseDTO patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patient/register_patient";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute("patient") PatientRequestDTO patientRequestDTO) {
        patientService.updatePatient(id, patientRequestDTO);
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return "redirect:/patients";
    }
}
