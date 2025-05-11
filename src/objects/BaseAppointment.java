package objects;

import java.time.LocalDateTime;

public abstract class BaseAppointment {
    protected String appointmentId;
    protected Patient patient;
    protected Doctor doctor;
    protected LocalDateTime dateTime;
    protected int durationMinutes;
    protected double cost;
    protected double hospitalRevenue;
    protected double privateDoctorRevenue;
    protected boolean isCompleted;
    protected String status;

    public BaseAppointment(String appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.durationMinutes = 30;
        this.isCompleted = false;
        this.status = "Scheduled";
    }

    public abstract double calculateCost();
    public abstract void handleStatusChange(String newStatus);

    // Common getters and setters
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

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes <= 0) {
            //error handle;
        }
        this.durationMinutes = durationMinutes;
    }

    public double getCost() {
        return cost;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getStatus() {
        return status;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
        if (completed) {
            handleStatusChange("Completed");
        } else {
            handleStatusChange("Scheduled");
        }
    }

    public void cancel() {
        handleStatusChange("Cancelled");
        System.out.println("Appointment cancelled");
    }

    public String GeneralInfo() {
        return "Appointment Information [ID: " + appointmentId + 
               ", Patient: " + patient.getFullName() + 
               ", Doctor: " + doctor.getFullName() +
               ", Date: " + dateTime + 
               ", Duration: " + durationMinutes + 
               ", Cost: " + cost + 
               ", Status: " + status + "]";
    }

    public void setStatus(String status) {
        handleStatusChange(status);
    }
} 