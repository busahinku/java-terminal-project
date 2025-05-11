package objects;

import java.time.LocalDateTime;

public class EmergencyAppointment extends BaseAppointment {
    private CostCalculationStrategy costStrategy;
    private static double EMERGENCY_MULTIPLIER = 1.5; // 50% more expensive

    public EmergencyAppointment(String appointmentId, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        super(appointmentId, patient, doctor, dateTime);
        if (doctor.isPrivate()) {
            this.costStrategy = new PrivateDoctorCostStrategy();
        } else {
            this.costStrategy = new HospitalDoctorCostStrategy();
        }
        calculateAndSetCosts();
    }

    private void calculateAndSetCosts() {
        double baseCost = costStrategy.calculateCost(doctor, durationMinutes);
        this.cost = baseCost * EMERGENCY_MULTIPLIER;
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
            System.out.println("EMERGENCY Appointment " + appointmentId
                    + getPatient().getFullName() + "->" + getDoctor().getFullName() +
                " completed");
        }
    }
} 