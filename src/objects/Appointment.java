package objects;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String status;
    private double cost;
    private boolean paid;
    private int durationMinutes; // Default duration in minutes

    public Appointment(String appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = "Scheduled";
        this.durationMinutes = 30; // Default 30-minute appointment
        this.cost = doctor.isPrivateDoctor() ? doctor.getConsultationFee() : 50.0;
        this.paid = false;
    }

    public Appointment(Patient patient, Doctor doctor, StaticSchedule.Day day, LocalTime time) {
        this.appointmentId = "APP" + System.currentTimeMillis();
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = LocalDateTime.of(2024, 1, 1, time.getHour(), time.getMinute());
        this.status = "Scheduled";
        this.durationMinutes = 30; // Default 30-minute appointment
        this.cost = doctor.isPrivateDoctor() ? doctor.getConsultationFee() : 50.0;
        this.paid = false;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        // Recalculate cost based on new duration
        if (doctor.isPrivateDoctor()) {
            this.cost = doctor.getConsultationFee() * (durationMinutes / 30.0); // Adjust cost based on duration
        }
    }

    public String GeneralInfo() {
        return String.format("Appointment ID: %s\nPatient: %s\nDoctor: %s\nDate: %s\nDuration: %d minutes\nStatus: %s\nCost: $%.2f\nPaid: %s",
                appointmentId,
                patient.getFullName(),
                doctor.getFullName(),
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                durationMinutes,
                status,
                cost,
                paid ? "Yes" : "No");
    }
}
