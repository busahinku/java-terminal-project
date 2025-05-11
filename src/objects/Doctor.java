package objects;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    public static int id = 0000000;
    private String department;
    private String specialty;
    private String officeNumber;
    private short experience;
    private boolean isPrivate;
    private double salary; // Monthly salary for hospital doctors
    private double appointmentFee; // For private doctors
    private String privatePracticeLocation; // For private doctors only
    private List<Appointment> appointments;
    private List<Patient> patients;
    private StaticSchedule staticSchedule;
    private List<Review> reviews;
    private short maxPatientsPerDay;
    private double consultationFee;

    public Doctor(String id, String firstName, String lastName, int age, char gender, String phoneNumber,
                 String username, String password, String department, String specialty, String officeNumber,
                 short maxPatientsPerDay, boolean isPrivate, double salary) {
        super(id, firstName, lastName, age, gender, phoneNumber, username, password);
        this.department = department;
        this.specialty = specialty;
        this.officeNumber = officeNumber;
        this.maxPatientsPerDay = maxPatientsPerDay;
        this.isPrivate = isPrivate;
        this.salary = salary;
        this.consultationFee = isPrivate ? 100.0 : 0.0; // Default consultation fee for private doctors
        this.appointments = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.staticSchedule = new StaticSchedule();
    }

    // Getters
    public String getDepartment() {
        return department;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public short getExperience() {
        return experience;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public double getSalary() {
        return salary;
    }

    public double getAppointmentFee() {
        return appointmentFee;
    }

    public String getPrivatePracticeLocation() {
        return privatePracticeLocation;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public StaticSchedule getStaticSchedule() {
        return staticSchedule;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public boolean isPrivateDoctor() {
        return isPrivate;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    // Setters
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAppointmentFee(double appointmentFee) {
            this.appointmentFee = appointmentFee;
    }

    public void setPrivatePracticeLocation(String location) {
            this.privatePracticeLocation = location;
    }

    public void setStaticSchedule(StaticSchedule schedule) {
        this.staticSchedule = schedule;
    }

    public void setConsultationFee(double fee) {
        if (isPrivate) {
            this.consultationFee = fee;
        }
    }

    // Methods
    public void addReview(Review review) {
        if (!reviews.contains(review)) {
            reviews.add(review);
        }
    }

    public double calculateAverageRating() {
        if (reviews.isEmpty()) return 0.0;
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    public double calculateAppointmentCost(int durationMinutes) {
        if (isPrivate) {
            return appointmentFee * durationMinutes;
        } else {
            return (salary / (30 * 10 * 60)) * durationMinutes; // day * hour * minute
        }
    }

    @Override
    public String GeneralInfo() {
        String Info = "Doctor Name: " + getFullName() +
                ", Specialty: " + specialty +
                ", Experience: " + experience + " years.";

        String result;
        if (isPrivate) {
            result = Info + ", Private Practice at: " + privatePracticeLocation +
                    ", Fee: $" + appointmentFee;
        } else {
            result = Info + ", Department: " + department +
                    ", Office: " + officeNumber;
        }
        return result;
    }

    public boolean isAvailable(StaticSchedule.Day day, LocalTime time) {
    
        if (!staticSchedule.isTimeSlotAvailable(day, time) || staticSchedule == null ) {
            return false;
        }

        // Check for overlapping appointments
        for (Appointment appointment : appointments) {
            // Convert appointment time to StaticSchedule.Day for comparison
            StaticSchedule.Day appointmentDay = StaticSchedule.Day.values()[appointment.getDateTime().toLocalDate().getDayOfWeek().getValue() - 1];
            LocalTime appointmentTime = appointment.getDateTime().toLocalTime();
            
            if (appointmentDay == day && appointmentTime.equals(time)) {
                return false;
            }
        }
        return true;
    }

    public Appointment scheduleAppointment(Patient patient, StaticSchedule.Day day, LocalTime time) {
        if (appointments.size() >= maxPatientsPerDay) {
            System.out.println("Maximum number of appointments reached for today.");
            return null;
        }

        Appointment appointment = new Appointment(patient, this, day, time);
        appointments.add(appointment);
        patient.addAppointment(appointment);
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
        return appointment;
    }
}
