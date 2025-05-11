package objects;

public class PrivateDoctorCostStrategy implements CostCalculationStrategy {
    @Override
    public double calculateCost(Doctor doctor, int duration) {
        return doctor.getAppointmentFee() * duration;
    }

    @Override
    public double calculateHospitalRevenue(double totalCost) {
        return totalCost * 0.10; // 10% goes to hospital
    }

    @Override
    public double calculateDoctorRevenue(double totalCost) {
        return totalCost * 0.90; // 90% goes to private doctor
    }
} 