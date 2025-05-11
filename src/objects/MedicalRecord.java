package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String recordId;
    private Patient patient;
    private String diagnoses;
    private String procedures;
    private List<String> medications;
    private String allergies;
    private String immunizations;
    private String labResults;
    private LocalDateTime lastUpdated;
    private String bloodType;
    private double height;
    private double weight;
    private String notes;

    public MedicalRecord(String recordId, Patient patient, String bloodType,
                         double height, double weight, String diagnoses, String procedures,
                         String allergies, String immunizations, String labResults, String notes) {
        this.recordId = recordId;
        this.patient = patient;
        this.bloodType = bloodType;
        this.height = height;
        this.weight = weight;
        this.diagnoses = diagnoses;
        this.procedures = procedures;
        this.medications = new ArrayList<>();
        this.allergies = allergies;
        this.immunizations = immunizations;
        this.labResults = labResults;
        this.lastUpdated = LocalDateTime.now();
        this.notes = notes;
    }

    // Getters
    public String getRecordId() {
        return recordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public String getProcedures() {
        return procedures;
    }

    public List<String> getMedications() {
        return new ArrayList<>(medications);
    }

    public String getAllergies() {
        return allergies;
    }

    public String getImmunizations() {
        return immunizations;
    }

    public String getLabResults() {
        return labResults;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getBloodType() {
        return bloodType;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setRecordId(String recordId) {
        this.recordId = recordId;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setMedications(List<String> medications) {
        this.medications = new ArrayList<>(medications);
        this.lastUpdated = LocalDateTime.now();
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setImmunizations(String immunizations) {
        this.immunizations = immunizations;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setLabResults(String labResults) {
        this.labResults = labResults;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weight = weight;
        this.lastUpdated = LocalDateTime.now();
    }

    public void setNotes(String notes) {
        this.notes = notes;
        this.lastUpdated = LocalDateTime.now();
    }

    // Convenience methods for medications
    public void addMedication(String medication) {
        this.medications.add(medication);
        this.lastUpdated = LocalDateTime.now();
    }

    public void removeMedication(String medication) {
        this.medications.remove(medication);
        this.lastUpdated = LocalDateTime.now();
    }

    public void clearMedications() {
        this.medications.clear();
        this.lastUpdated = LocalDateTime.now();
    }

    public String GeneralInfo() {
        return "Medical Record ID: " + recordId +
                ", Patient: " + patient.getFullName() +
                ", Last Updated: " + lastUpdated +
                ", Diagnoses: " + diagnoses +
                ", Procedures: " + procedures;
    }
} 