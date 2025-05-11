package objects;

import java.time.LocalDateTime;

public class Room {
    private String roomName;
    private String roomType;
    private int capacity;
    private boolean isAvailable;
    private Patient currentPatient;
    private LocalDateTime occupiedSince;
    private double hourlyRate;
    private String equipment;

    public Room(String roomName, String roomType, int capacity, double hourlyRate, String equipment) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.capacity = capacity;
        this.isAvailable = true;
        this.hourlyRate = hourlyRate;
        this.equipment = equipment;
    }

    // Getters
    public String getRoomName() {
        return roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public String getEquipment() {
        return equipment;
    }

    // Methods
    public void assignPatient(Patient patient) {
        if (this.currentPatient == null) {
            this.currentPatient = patient;
            this.isAvailable = false;
            this.occupiedSince = LocalDateTime.now();
            System.out.println("Patient " + patient.getFullName() + " assigned to room " + roomName);
        } else {
            System.out.println("Room " + roomName + " is already occupied");
        }
    }

    public String GeneralInfo() {
        String status;
        if (currentPatient != null) {
            status = "Occupied";
        } else {
            status = "Available";
        }

        return "Room Name: " + roomName +
                ", Type: " + roomType +
                ", Rate: $" + hourlyRate + "/hr" +
                ", Equipment: " + equipment +
                ", Status: " + status + "";
    }
}