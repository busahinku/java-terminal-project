package objects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String recordId;
    private Patient patient;
    private List<String> diagnoses;
    private List<String> procedures;
    private List<String> medications;
    private List<String> allergies;
    private List<String> immunizations;
    private List<String> labResults;
    private LocalDateTime lastUpdated;
    private String bloodType;
    private double height;
    private double weight;
    private String notes;

    public MedicalRecord(String recordId, Patient patient, String bloodType, double height, double weight) {
        this.recordId = recordId;
        this.patient = patient;
        this.bloodType = bloodType;
        this.height = height;
        this.weight = weight;
        this.diagnoses = new ArrayList<>();
        this.procedures = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();
        this.immunizations = new ArrayList<>();
        this.labResults = new ArrayList<>();
        this.lastUpdated = LocalDateTime.now();
        this.notes = "";
    }

    public MedicalRecord(Patient patient) {
        this.recordId = null;
        this.patient = patient;
        this.bloodType = null;
        this.height = 000;
        this.weight = 000;
        this.diagnoses = new ArrayList<>();
        this.procedures = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.allergies = new ArrayList<>();
        this.immunizations = new ArrayList<>();
        this.labResults = new ArrayList<>();
        this.lastUpdated = LocalDateTime.now();
        this.notes = "";
    }

    // Getters
    public String getRecordId() {
        return recordId;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<String> getDiagnoses() {
        return new ArrayList<>(diagnoses);
    }

    public List<String> getProcedures() {
        return new ArrayList<>(procedures);
    }

    public List<String> getMedications() {
        return new ArrayList<>(medications);
    }

    public List<String> getAllergies() {
        return new ArrayList<>(allergies);
    }

    public List<String> getImmunizations() {
        return new ArrayList<>(immunizations);
    }

    public List<String> getLabResults() {
        return new ArrayList<>(labResults);
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

    // Methods
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
        updateLastModified();
    }

    public void addProcedure(String procedure) {
        procedures.add(procedure);
        updateLastModified();
    }

    public void addMedication(String medication) {
        medications.add(medication);
        updateLastModified();
    }

    public void addAllergy(String allergy) {
        allergies.add(allergy);
        updateLastModified();
    }

    public void addImmunization(String immunization) {
        immunizations.add(immunization);
        updateLastModified();
    }

    public void addLabResult(String labResult) {
        labResults.add(labResult);
        updateLastModified();
    }


    public void addNote(String note) {
        this.notes += "\n" + LocalDateTime.now() + ": " + note;
        updateLastModified();
    }

    private void updateLastModified() {
        this.lastUpdated = LocalDateTime.now();
    }

    public String GeneralInfo() {
        return "Medical Record ID: " + recordId +
                ", Patient: " + patient.getFullName() +
                ", Last Updated: " + lastUpdated +
                ", Diagnoses: " + diagnoses.size() +
                ", Procedures: " + procedures.size();
    }
} 