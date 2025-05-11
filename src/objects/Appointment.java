package objects;

import java.time.LocalDateTime;

public class Appointment extends BaseAppointment {
    private final CostCalculationStrategy costStrategy;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        super(appointmentId, patient, doctor, dateTime);
        if (doctor.isPrivate()) {
            this.costStrategy = new PrivateDoctorCostStrategy();
        } else {
            this.costStrategy = new HospitalDoctorCostStrategy();
        }
        calculateAndSetCosts();
    }

    private void calculateAndSetCosts() {
        this.cost = costStrategy.calculateCost(doctor, durationMinutes);
        this.hospitalRevenue = costStrategy.calculateHospitalRevenue(cost);
        this.privateDoctorRevenue = costStrategy.calculateDoctorRevenue(cost);
    }

    @Override
    public double calculateCost() {
        return cost;
    }

    @Override
    public void handleStatusChange(String newStatus) {
        this.status = newStatus;
        if (newStatus.equals("Completed")) {
            System.out.println("Appointment " + appointmentId
                    + getPatient().getFullName() + "->" + getDoctor().getFullName() +
                " completed");
        }
    }
}
