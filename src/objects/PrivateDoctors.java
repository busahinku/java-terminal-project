package objects;

import java.util.ArrayList;
import java.util.List;

public class PrivateDoctors extends Person {
    private String clinicName;
    private String clinicAddress;
    private String specialty;
    private double appointmentFee; // per minute
    private short experience;
    private List<Appointment> appointments;
    private static final double HOSPITAL_FEE_PERCENTAGE = 0.10; // 10% goes to hospital

    public PrivateDoctors(String id, String firstName, String lastName, int age, char gender,
                          String phoneNumber, String username, String password,
                          String clinicName, String clinicAddress, String specialty,
                          double appointmentFee, short experience) {
        super(id, firstName, lastName, age, gender, phoneNumber, username, password);
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.specialty = specialty;
        this.appointmentFee = appointmentFee;
        this.experience = experience;
        this.appointments = new ArrayList<>();
    }

    // Getters
    public String getClinicName() {
        return clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public String getSpecialty() {
        return specialty;
    }

    public double getAppointmentFee() {
        return appointmentFee;
    }

    public short getExperience() {
        return experience;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    // Setters
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public void setAppointmentFee(double appointmentFee) {
        this.appointmentFee = appointmentFee;
    }

    // Methods
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public double calculateAppointmentCost(int durationMinutes) {
        return appointmentFee * durationMinutes;
    }

    public double calculateHospitalFee(int durationMinutes) {
        return calculateAppointmentCost(durationMinutes) * HOSPITAL_FEE_PERCENTAGE;
    }

    public double calculateDoctorEarnings(int durationMinutes) {
        return calculateAppointmentCost(durationMinutes) * (1 - HOSPITAL_FEE_PERCENTAGE);
    }

    public double calculateTotalSalary() {
        double totalSalary = 0.0;
        for (Appointment appointment : appointments) {
            totalSalary += calculateDoctorEarnings(appointment.getDurationMinutes());
        }
        return totalSalary;
    }

    @Override
    public String GeneralInfo() {
        return "Private Doctor Name: " + getFullName() +
                ", Clinic: " + clinicName +
                ", Address: " + clinicAddress +
                ", Specialty: " + specialty +
                ", Fee: $" + appointmentFee + "/min" +
                ", Experience: " + experience + " years" +
                ", Total Salary: $" + calculateTotalSalary();
    }
}
