package objects;

public class HospitalDoctorCostStrategy implements CostCalculationStrategy {
    @Override
    public double calculateCost(Doctor doctor, int duration) {
        return doctor.calculateAppointmentCost(duration);
    }

    @Override
    public double calculateHospitalRevenue(double totalCost) {
        return totalCost; // 100% goes to hospital
    }

    @Override
    public double calculateDoctorRevenue(double totalCost) {
        return 0.0; // Hospital doctors don't get direct revenue
    }
}