package objects;

public interface CostCalculationStrategy {
    double calculateCost(Doctor doctor, int duration);
    double calculateHospitalRevenue(double totalCost);
    double calculateDoctorRevenue(double totalCost);
} 