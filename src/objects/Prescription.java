package objects;

import java.time.LocalDateTime;

public class Prescription {
    private String medication;
    private String dosageType;
    private String usage;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime issueDate;
    private String notes;

    public Prescription(String medication, String dosageType, String usage,
                       Patient patient, Doctor doctor, String notes) {
        this.medication = medication;
        this.dosageType = dosageType;
        this.usage = usage;
        this.patient = patient;
        this.doctor = doctor;
        this.issueDate = LocalDateTime.now();
        this.notes = notes;
    }

    // Getters
    public String getMedication() {
        return medication;
    }

    public String getDosageType() {
        return dosageType;
    }

    public String getUsage() {
        return usage;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public String getNotes() {
        return notes;
    }

    public String GeneralInfo() {
        return "Prescription Medication: " + medication +
                ", Type: " + dosageType +
                ", Usage: " + usage +
                ", Patient: " + patient.getFullName() +
                ", Doctor: " + doctor.getFullName() +
                ", Date: " + issueDate;
    }
}
