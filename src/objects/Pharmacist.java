package objects;

import java.util.ArrayList;
import java.util.List;

public class Pharmacist extends Person {
    private String location;
    private List<String> medications;
    private double salary;
    private String workSchedule;
    private List<Prescription> prescriptions;

    public Pharmacist(String id, String firstName, String lastName, int age, char gender,
                     String phoneNumber, String username, String password,
                     String location, double salary, String workSchedule) {
        super(id, firstName, lastName, age, gender, phoneNumber, username, password);
        this.location = location;
        this.medications = new ArrayList<>();
        this.salary = salary;
        this.workSchedule = workSchedule;
        this.prescriptions = new ArrayList<>();
    }

    // Getters
    public String getLocation() {
        return location;
    }

    public List<String> getMedications() {
        return new ArrayList<>(medications);
    }

    public double getSalary() {
        return salary;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public List<Prescription> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    // Methods

    public void distributeMedication(Prescription prescription) {
        if (medications.contains(prescription.getMedication())) {
            prescriptions.add(prescription);
            System.out.println("Dispensing medication for prescription: " + prescription.GeneralInfo());
        } else {
            System.out.println("Cannot Distributed: Medication, because " + prescription.getMedication() + " is out of stock");
        }
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
    }

    @Override
    public String GeneralInfo() {
        return "Pharmacist [Name: " + getFullName() +
                ", Location: " + location +
                ", Schedule: " + workSchedule + "]";
    }
}
