package objects;

public class Assistant extends Person {
    private Doctor supervisor;
    private short experience;
    private String duty;
    private Department department;
    private double salary;
    private String workSchedule;

    public Assistant(String id, String firstName, String lastName, int age, char gender,
                    String phoneNumber, String username, String password,
                    Doctor supervisor, short experience, String duty,
                    Department department, double salary, String workSchedule) {
        super(id, firstName, lastName, age, gender, phoneNumber, username, password);
        this.supervisor = supervisor;
        this.experience = experience;
        this.duty = duty;
        this.department = department;
        this.salary = salary;
        this.workSchedule = workSchedule;
    }

    // Getters
    public Doctor getSupervisor() {
        return supervisor;
    }

    public short getExperience() {
        return experience;
    }

    public String getDuty() {
        return duty;
    }

    // Setters
    public void setSupervisor(Doctor supervisor) {
        this.supervisor = supervisor;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    // String Methods These not return something so we cant use these from JavaFX application
    public void callDoctor(Doctor doctor) {
        System.out.println("Assistant " + getFullName() + " calling Dr. " + doctor.getFullName());
    }

    public void updateSchedule(Doctor doctor, String schedule) {
        System.out.println("Schedule updated for Dr. " + doctor.getFullName() + ": " + schedule);
    }

    public void manageAppointment(Appointment appointment) {
        System.out.println("Assistant " + getFullName() + " managing appointment for " + 
                         appointment.getPatient().getFullName() + " with Dr. " + 
                         appointment.getDoctor().getFullName());
    }

    @Override
    public String GeneralInfo() {
        return "Assistant Name: " + getFullName() + ", Department: " + department.getName() + ", Duty: " + duty + ", Supervisor: " + supervisor.getFullName() + "";
    }
}
