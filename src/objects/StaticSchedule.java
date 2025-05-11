package objects;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;


//--CHAT GPT SCHEDULING PART--
public class StaticSchedule {
    // Define the fixed time slots from 9 AM to 5 PM with 30-minute intervals
    public static final List<LocalTime> TIME_SLOTS = new ArrayList<>();
    static {
        // Initialize time slots from 9:00 to 16:30 (5:30 PM)
        for (int hour = 9; hour <= 16; hour++) {
            for (int minute = 0; minute <= 30; minute += 30) {
                TIME_SLOTS.add(LocalTime.of(hour, minute));
            }
        }
    }

    // Define the working days (Monday to Friday)
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }

    // Get all available time slots for a specific day
    public List<LocalTime> getAvailableTimeSlots(Day day, List<Appointment> appointments) {
        List<LocalTime> availableSlots = new ArrayList<>(TIME_SLOTS);
        
        // Remove slots that are already booked
        for (Appointment appointment : appointments) {
            // Convert appointment time to StaticSchedule.Day for comparison
            StaticSchedule.Day appointmentDay = StaticSchedule.Day.values()[appointment.getDateTime().toLocalDate().getDayOfWeek().getValue() - 1];
            LocalTime appointmentTime = appointment.getDateTime().toLocalTime();
            
            if (appointmentDay == day) {
                availableSlots.remove(appointmentTime);
            }
        }
        return availableSlots;
    }

    // Get a formatted string of all available time slots
    public String getAvailableTimeSlotsAsString(Day day, List<Appointment> appointments) {
        StringBuilder sb = new StringBuilder("Available time slots for " + day + ":\n");
        List<LocalTime> availableSlots = getAvailableTimeSlots(day, appointments);
        for (LocalTime time : availableSlots) {
            sb.append(time).append("\n");
        }
        return sb.toString();
    }

    // Get all available days
    public List<Day> getAvailableDays() {
        return List.of(Day.values());
    }

    // Check if a specific time slot is available
    public boolean isTimeSlotAvailable(Day day, LocalTime time) {
        return TIME_SLOTS.contains(time);
    }

    // Get a formatted string of all available time slots
    public String getAvailableTimeSlotsAsString(Day day) {
        StringBuilder sb = new StringBuilder("Available time slots for " + day + ":\n");
        for (LocalTime time : TIME_SLOTS) {
            sb.append(time).append("\n");
        }
        return sb.toString();
    }
}
