package objects;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private Doctor head;
    private String location;
    private List<Doctor> doctors;

    public Department(String name, Doctor head, String location) {
        this.name = name;
        this.head = head;
        this.location = location;
        this.doctors = new ArrayList<>();
        if (head != null) {
            this.doctors.add(head);
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public Doctor getHead() {
        return head;
    }

    public String getLocation() {
        return location;
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    // Setters
    public void setHead(Doctor head) {
        this.head = head;
        if (!doctors.contains(head)) {
            doctors.add(head);
        }
    }

    public void addDoctor(Doctor doctor) {
        if (!doctors.contains(doctor)) {
            doctors.add(doctor);
        }
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
        if (head == doctor) {
            head = null;
        }
    }

    public String GeneralInfo() {
        String info = "Department Name: " + name +
                      "| Location: " + location +
                      "| Head: " + head.getFullName() +
                      "\nDoctors in this department:\n";
        for (Doctor doctor : doctors) {
            info += "- " + doctor.getFullName() + " | " + doctor.getSpecialty() + " |\n";
        }
        return info;
    }
}
