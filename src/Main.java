

import objects.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Person> users = new ArrayList<>();
    private static Person currentUser = null;
    private static List<Department> departments = new ArrayList<>();
    private static List<Inventory> inventoryList = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        while (true) {
            if (currentUser == null) {
                showWelcomeMenu();
            } else {
                showRoleSpecificMenu();
            }
        }
    }

    private static void initializeData() {
        // Create departments
        Department cardiology = new Department("Cardiology", null, "Block A");
        Department neurology = new Department("Neurology", null, "Block B");
        departments.add(cardiology);
        departments.add(neurology);

        // Create founder
        Founder founder = new Founder("F001", "Aysen", "Akkaya", 40, 'F', "5319870221",
                "founder", "founder", 150000.0);

        // Create doctors
        Doctor cardiologist = new Doctor("D001", "Aysegul", "Eren", 30, 'F', "5245287101",
                "doctor", "doctor", "Cardiology", "Cardiologist", "301", (short)15, false, 120000.0);
        cardiologist.setStaticSchedule(new StaticSchedule());

        Doctor neurologist = new Doctor("D002", "Ates", "Hekimoglu", 40, 'M', "512389722",
                "ates", "ates", "Neurology", "Neurologist", "302", (short)12, false, 110000.0);
        neurologist.setStaticSchedule(new StaticSchedule());

        // Create patient
        Patient patient = new Patient("P001", "Burakcim", "Sahin", 35, 'M', "5319878790",
                "patient", "patient", true, "Blue Cross", "BC123456");

        // Create pharmacist
        Pharmacist pharmacist = new Pharmacist("PH001", "Kevin De", "Bruyne", 35, 'M', "52418176236",
                "pharmacist", "pharmacist", "Main Pharmacy", 80000.0, "Monday-Friday 9AM-5PM");

        // Create assistant (example: supervised by neurologist, in neurology department)
        Assistant assistant = new Assistant(
                "A001", "Lewis", "Hamilton", 35, 'M', "52418176236",
                "assistant", "assistant",
                neurologist, (short)5, "Adissed",
                neurology, 100000.0, "9:5"
        );
        users.add(assistant);

        // Assign doctors to departments
        cardiology.setHead(cardiologist);
        neurology.setHead(neurologist);

        // Add users
        users.add(founder);
        users.add(cardiologist);
        users.add(neurologist);
        users.add(patient);
        users.add(pharmacist);

        // Sample inventory items
        inventoryList.add(new Inventory("I001", "Paracetamol", "Medication", 100, 10, 1.5, "PharmaSupplier", "Main Pharmacy"));
        inventoryList.add(new Inventory("I002", "Bandage", "Medical Supplies", 50, 5, 0.5, "MedSupplyCo", "Main Storage"));
        inventoryList.add(new Inventory("I003", "Ibuprofen", "Medication", 80, 10, 2.0, "PharmaSupplier", "Main Pharmacy"));
    }

    private static void showWelcomeMenu() {
        while (true) {
            System.out.println("\n=== Welcome to Hospital Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Register (Patient)");
            System.out.println("3. Exit");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    login();
                    if (currentUser != null) {
                        return;
                    }
                    break;
                case "2":
                    registerPatient();
                    break;
                case "3":
                    System.out.println("\nThank you for using Hospital Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerPatient() {
        System.out.println("\n=== Patient Registration ===");

        // Generate a new patient ID
        String patientId = "P" + String.format("%03d", users.stream()
                .filter(u -> u instanceof Patient)
                .count() + 1);

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Gender (M/F): ");
        char gender = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Do you have insurance? (yes/no): ");
        boolean hasInsurance = scanner.nextLine().toLowerCase().startsWith("y");

        String insuranceProvider = "";
        String insurancePolicyNumber = "";
        if (hasInsurance) {
            System.out.print("Insurance Provider: ");
            insuranceProvider = scanner.nextLine();

            System.out.print("Insurance Policy Number: ");
            insurancePolicyNumber = scanner.nextLine();
        }

        // Create new patient
        Patient newPatient = new Patient(
                patientId,
                firstName,
                lastName,
                age,
                gender,
                phoneNumber,
                username,
                password,
                hasInsurance,
                insuranceProvider,
                insurancePolicyNumber
        );

        // Add to users list
        users.add(newPatient);

        System.out.println("\nRegistration successful! You can now login with your credentials.");
    }

    private static void login() {
        System.out.println("\n=== Hospital Management System ===");
        System.out.println("Please login:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (Person user : users) {
            if (user.authenticate(username, password)) {
                currentUser = user;
                System.out.println("\nWelcome, " + user.getFullName() + "!");
                return;
            }
        }
        System.out.println("\nInvalid username or password. Please try again.");
    }

    private static void showRoleSpecificMenu() {
        if (currentUser instanceof Founder) {
            showFounderMenu();
        } else if (currentUser instanceof Doctor) {
            showDoctorMenu();
        } else if (currentUser instanceof Patient) {
            showPatientMenu();
        } else if (currentUser instanceof Pharmacist) {
            showPharmacistMenu();
        } else if (currentUser instanceof Assistant) {
            showAssistantMenu();
        }
    }

    private static void showFounderMenu() {
        while (true) {
            System.out.println("\n=== Founder Dashboard ===");
            System.out.println("1. View Profile");
            System.out.println("2. Create Department");
            System.out.println("3. Create Doctor");
            System.out.println("4. Create Assistant");
            System.out.println("5. Hire Doctor");
            System.out.println("6. Fire Doctor");
            System.out.println("7. View All Workers");
            System.out.println("8. View All Departments");
            System.out.println("9. Generate Monthly Report");
            System.out.println("10. Logout");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showProfile();
                    break;
                case "2":
                    createDepartment();
                    break;
                case "3":
                    createDoctor();
                    break;
                case "4":
                    createAssistant();
                    break;
                case "5":
                    hireDoctor();
                    break;
                case "6":
                    fireDoctor();
                    break;
                case "7":
                    viewAllWorkers();
                    break;
                case "8":
                    viewAllDepartments();
                    break;
                case "9":
                    generateMonthlyReport();
                    break;
                case "10":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showDoctorMenu() {
        while (true) {
            System.out.println("\n=== Doctor Dashboard ===");
            System.out.println("1. View Profile");
            System.out.println("2. View Appointments");
            System.out.println("3. View Patients");
            System.out.println("4. Write Prescription");
            System.out.println("5. Update Medical Records");
            System.out.println("6. View Schedule");
            System.out.println("7. Complete/Cancel Appointment");
            System.out.println("8. Logout");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showProfile();
                    break;
                case "2":
                    viewAppointments();
                    break;
                case "3":
                    viewPatients();
                    break;
                case "4":
                    writePrescription();
                    break;
                case "5":
                    updateMedicalRecords();
                    break;
                case "6":
                    viewSchedule();
                    break;
                case "7":
                    manageDoctorAppointments();
                    break;
                case "8":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showPatientMenu() {
        while (true) {
            System.out.println("\n=== Patient Dashboard ===");
            System.out.println("1. View Profile");
            System.out.println("2. View Appointments");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Medical Records");
            System.out.println("5. View Prescriptions");
            System.out.println("6. Write Review");
            System.out.println("7. See Reviews");
            System.out.println("8. Billing");
            System.out.println("9. Logout");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showProfile();
                    break;
                case "2":
                    viewAppointments();
                    break;
                case "3":
                    createAppointment();
                    break;
                case "4":
                    viewMedicalRecords();
                    break;
                case "5":
                    viewPrescriptions();
                    break;
                case "6":
                    writeReview();
                    break;
                case "7":
                    seeReviews();
                    break;
                case "8":
                    showBilling();
                    break;
                case "9":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showPharmacistMenu() {
        Pharmacist pharmacist = (Pharmacist) currentUser;
        while (true) {
            System.out.println("\n=== Pharmacist Dashboard ===");
            System.out.println("1. View Profile");
            System.out.println("2. View Inventory");
            System.out.println("3. Add Inventory Stock");
            System.out.println("4. Remove Inventory Stock");
            System.out.println("5. Check Medication Stock");
            System.out.println("6. View Prescriptions");
            System.out.println("7. Dispense Medication");
            System.out.println("8. Logout");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(pharmacist.GeneralInfo());
                    break;
                case "2":
                    viewInventory();
                    break;
                case "3":
                    addInventoryStock();
                    break;
                case "4":
                    removeInventoryStock();
                    break;
                case "5":
                    checkMedicationStock();
                    break;
                case "6":
                    viewPrescriptionsPharmacist(pharmacist);
                    break;
                case "7":
                    dispenseMedication(pharmacist);
                    break;
                case "8":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Inventory management helpers
    private static void viewInventory() {
        System.out.println("\n=== Inventory List ===");
        if (inventoryList == null || inventoryList.isEmpty()) {
            System.out.println("No inventory items found.");
            return;
        }
        for (Inventory item : inventoryList) {
            System.out.println(item.GeneralInfo());
        }
    }

    private static void addInventoryStock() {
        System.out.println("\n=== Add Inventory Stock ===");
        viewInventory();
        System.out.print("Enter Item ID to add stock: ");
        String itemId = scanner.nextLine();
        Inventory selected = null;
        for (Inventory item : inventoryList) {
            if (item.getItemId().equals(itemId)) {
                selected = item;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter amount to add: ");
        int amount = Integer.parseInt(scanner.nextLine());
        selected.addStock(amount);
        System.out.println("Stock added. New quantity: " + selected.getQuantity());
    }

    private static void removeInventoryStock() {
        System.out.println("\n=== Remove Inventory Stock ===");
        viewInventory();
        System.out.print("Enter Item ID to remove stock: ");
        String itemId = scanner.nextLine();
        Inventory selected = null;
        for (Inventory item : inventoryList) {
            if (item.getItemId().equals(itemId)) {
                selected = item;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter amount to remove: ");
        int amount = Integer.parseInt(scanner.nextLine());
        selected.removeStock(amount);
        System.out.println("Stock removed. New quantity: " + selected.getQuantity());
    }

    private static void checkMedicationStock() {
        System.out.println("\n=== Check Medication Stock ===");
        System.out.print("Enter medication name: ");
        String medName = scanner.nextLine();
        boolean found = false;
        for (Inventory item : inventoryList) {
            if (item.getItemName().equalsIgnoreCase(medName)) {
                System.out.println(item.getItemName() + " in stock: " + item.getQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Medication not found in inventory.");
        }
    }

    // Prescription management helpers
    private static void viewPrescriptionsPharmacist(Pharmacist pharmacist) {
        System.out.println("\n=== Prescriptions ===");
        if (pharmacist.getPrescriptions().isEmpty()) {
            System.out.println("No prescriptions found.");
        } else {
            for (Prescription prescription : pharmacist.getPrescriptions()) {
                System.out.println(prescription.GeneralInfo());
            }
        }
    }

    private static void dispenseMedication(Pharmacist pharmacist) {
        System.out.println("\n=== Dispense Medication ===");
        // For demo: show all patients and their prescriptions
        List<Prescription> allPrescriptions = new ArrayList<>();
        for (Person user : users) {
            if (user instanceof Patient) {
                allPrescriptions.addAll(((Patient) user).getPrescriptions());
            }
        }
        if (allPrescriptions.isEmpty()) {
            System.out.println("No prescriptions to dispense.");
            return;
        }
        for (Prescription prescription : allPrescriptions) {
            System.out.println(prescription.GeneralInfo());
        }
        System.out.print("Enter medication name to dispense: ");
        String medName = scanner.nextLine();
        Prescription selected = null;
        for (Prescription prescription : allPrescriptions) {
            if (prescription.getMedication().equalsIgnoreCase(medName)) {
                selected = prescription;
                break;
            }
        }
        if (selected == null) {
            System.out.println("Prescription not found.");
            return;
        }
        pharmacist.distributeMedication(selected);
    }

    private static void showAssistantMenu() {
        Assistant assistant = (Assistant) currentUser;
        while (true) {
            System.out.println("\n=== Assistant Dashboard ===");
            System.out.println("1. View Profile");
            System.out.println("2. Call Doctor");
            System.out.println("3. Update Doctor Schedule");
            System.out.println("4. Manage Appointment");
            System.out.println("5. Logout");
            System.out.print("\nSelect an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println(assistant.GeneralInfo());
                    break;
                case "2":
                    callDoctorByAssistant(assistant);
                    break;
                case "3":
                    updateDoctorScheduleByAssistant(assistant);
                    break;
                case "4":
                    manageAppointmentByAssistant(assistant);
                    break;
                case "5":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void callDoctorByAssistant(Assistant assistant) {
        System.out.println("\nAvailable Doctors:");
        for (Person user : users) {
            if (user instanceof Doctor) {
                Doctor doc = (Doctor) user;
                System.out.println(doc.getId() + ": " + doc.getFullName());
            }
        }
        System.out.print("Enter Doctor ID to call: ");
        String docId = scanner.nextLine();
        Doctor selected = null;
        for (Person user : users) {
            if (user instanceof Doctor && ((Doctor) user).getId().equals(docId)) {
                selected = (Doctor) user;
                break;
            }
        }
        if (selected != null) {
            assistant.callDoctor(selected);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void updateDoctorScheduleByAssistant(Assistant assistant) {
        System.out.println("\nAvailable Doctors:");
        for (Person user : users) {
            if (user instanceof Doctor) {
                Doctor doc = (Doctor) user;
                System.out.println(doc.getId() + ": " + doc.getFullName());
            }
        }
        System.out.print("Enter Doctor ID to update schedule: ");
        String docId = scanner.nextLine();
        Doctor selected = null;
        for (Person user : users) {
            if (user instanceof Doctor && ((Doctor) user).getId().equals(docId)) {
                selected = (Doctor) user;
                break;
            }
        }
        if (selected != null) {
            System.out.print("Enter new schedule: ");
            String schedule = scanner.nextLine();
            assistant.updateSchedule(selected, schedule);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void manageAppointmentByAssistant(Assistant assistant) {
        System.out.println("\nAvailable Appointments:");
        List<Appointment> allAppointments = new ArrayList<>();
        for (Person user : users) {
            if (user instanceof Doctor) {
                Doctor doc = (Doctor) user;
                allAppointments.addAll(doc.getAppointments());
            }
        }
        if (allAppointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment apt : allAppointments) {
            System.out.println(apt.getAppointmentId() + ": Patient: " + apt.getPatient().getFullName() + ", Doctor: " + apt.getDoctor().getFullName());
        }
        System.out.print("Enter Appointment ID to manage: ");
        String aptId = scanner.nextLine();
        Appointment selected = null;
        for (Appointment apt : allAppointments) {
            if (apt.getAppointmentId().equals(aptId)) {
                selected = apt;
                break;
            }
        }
        if (selected != null) {
            assistant.manageAppointment(selected);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static void showProfile() {
        System.out.println("\n=== Profile ===");
        System.out.println("Name: " + currentUser.getFullName());
        System.out.println("Age: " + currentUser.getAge());
        System.out.println("Gender: " + currentUser.getGender());
        System.out.println("Phone: " + currentUser.getPhoneNumber());

        if (currentUser instanceof Doctor) {
            Doctor doctor = (Doctor) currentUser;
            System.out.println("Department: " + doctor.getDepartment());
            System.out.println("Specialty: " + doctor.getSpecialty());
            System.out.println("Office: " + doctor.getOfficeNumber());
        } else if (currentUser instanceof Patient) {
            Patient patient = (Patient) currentUser;
            System.out.println("Insurance: " + (patient.hasInsurance() ? "Yes" : "No"));
            if (patient.hasInsurance()) {
                System.out.println("Provider: " + patient.getInsuranceProvider());
                System.out.println("Policy Number: " + patient.getInsurancePolicyNumber());
            }
        }
    }

    private static void createAppointment() {
        if (currentUser instanceof Patient) {
            System.out.println("\n=== Create Appointment ===");

            // Show available doctors
            System.out.println("\nAvailable Doctors:");
            for (Person user : users) {
                if (user instanceof Doctor) {
                    Doctor doc = (Doctor) user;
                    System.out.println(doc.getId() + ": " + doc.getFullName() + " (" + doc.getSpecialty() + ")");
                }
            }

            System.out.print("\nSelect Doctor ID: ");
            String doctorId = scanner.nextLine();

            Doctor selectedDoctor = null;
            for (Person user : users) {
                if (user instanceof Doctor && ((Doctor) user).getId().equals(doctorId)) {
                    selectedDoctor = (Doctor) user;
                    break;
                }
            }

            if (selectedDoctor == null) {
                System.out.println("Invalid doctor selection.");
                return;
            }

            // Show available days
            System.out.println("\nAvailable Days:");
            for (StaticSchedule.Day day : StaticSchedule.Day.values()) {
                System.out.println((day.ordinal() + 1) + ". " + day);
            }

            System.out.print("\nSelect Day (1-5): ");
            int dayChoice = Integer.parseInt(scanner.nextLine());
            if (dayChoice < 1 || dayChoice > 5) {
                System.out.println("Invalid day selection.");
                return;
            }
            StaticSchedule.Day selectedDay = StaticSchedule.Day.values()[dayChoice - 1];

            // Show available time slots
            System.out.println("\nAvailable Time Slots:");
            List<LocalTime> availableSlots = selectedDoctor.getStaticSchedule()
                    .getAvailableTimeSlots(selectedDay, selectedDoctor.getAppointments());

            if (availableSlots.isEmpty()) {
                System.out.println("No available time slots for this day.");
                return;
            }

            for (int i = 0; i < availableSlots.size(); i++) {
                System.out.println((i + 1) + ". " + availableSlots.get(i));
            }

            System.out.print("\nSelect Time Slot (1-" + availableSlots.size() + "): ");
            int timeChoice = Integer.parseInt(scanner.nextLine());
            if (timeChoice < 1 || timeChoice > availableSlots.size()) {
                System.out.println("Invalid time slot selection.");
                return;
            }

            LocalTime selectedTime = availableSlots.get(timeChoice - 1);

            // Create appointment
            Appointment appointment = selectedDoctor.scheduleAppointment(
                    (Patient) currentUser,
                    selectedDay,
                    selectedTime
            );

            if (appointment != null) {
                System.out.println("\nAppointment created successfully!");
                System.out.println("Appointment ID: " + appointment.getAppointmentId());
                System.out.println("Doctor: " + appointment.getDoctor().getFullName());
                System.out.println("Date: " + appointment.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                System.out.println("Cost: $" + appointment.getCost());
            } else {
                System.out.println("Failed to create appointment.");
            }
        }
    }

    private static void viewAppointments() {
        System.out.println("\n=== Appointments ===");
        if (currentUser instanceof Patient) {
            Patient patient = (Patient) currentUser;
            List<Appointment> appointments = patient.getAppointments();
            if (appointments.isEmpty()) {
                System.out.println("No appointments found.");
            } else {
                for (Appointment apt : appointments) {
                    System.out.println("\nAppointment ID: " + apt.getAppointmentId());
                    System.out.println("Doctor: " + apt.getDoctor().getFullName());
                    System.out.println("Date: " + apt.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    System.out.println("Status: " + apt.getStatus());
                    System.out.println("Cost: $" + apt.getCost());
                }
            }
        } else if (currentUser instanceof Doctor) {
            Doctor doctor = (Doctor) currentUser;
            List<Appointment> appointments = doctor.getAppointments();
            if (appointments.isEmpty()) {
                System.out.println("No appointments found.");
            } else {
                for (Appointment apt : appointments) {
                    System.out.println("\nAppointment ID: " + apt.getAppointmentId());
                    System.out.println("Patient: " + apt.getPatient().getFullName());
                    System.out.println("Date: " + apt.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    System.out.println("Status: " + apt.getStatus());
                    boolean isPaid = false;
                    try {
                        isPaid = (boolean) apt.getClass().getMethod("isPaid").invoke(apt);
                    } catch (Exception e) {}
                    System.out.println("Paid: " + (isPaid ? "Yes" : "No"));
                }
            }
        }
    }

    private static void viewPatients() {
        if (currentUser instanceof Doctor) {
            System.out.println("\n=== Patients ===");
            Doctor doctor = (Doctor) currentUser;
            List<Patient> patients = doctor.getPatients();
            if (patients.isEmpty()) {
                System.out.println("No patients found.");
            } else {
                for (Patient patient : patients) {
                    System.out.println("\nPatient ID: " + patient.getId());
                    System.out.println("Name: " + patient.getFullName());
                    System.out.println("Age: " + patient.getAge());
                    System.out.println("Gender: " + patient.getGender());
                    System.out.println("Phone: " + patient.getPhoneNumber());
                }
            }
        }
    }

    private static void writePrescription() {
        if (currentUser instanceof Doctor) {
            System.out.println("\n=== Write Prescription ===");
            System.out.print("Patient ID: ");
            String patientId = scanner.nextLine();

            Patient selectedPatient = null;
            for (Person user : users) {
                if (user instanceof Patient && ((Patient) user).getId().equals(patientId)) {
                    selectedPatient = (Patient) user;
                    break;
                }
            }

            if (selectedPatient != null) {
                System.out.print("Medication: ");
                String medication = scanner.nextLine();
                System.out.print("Dosage Type: ");
                String dosageType = scanner.nextLine();
                System.out.print("Usage Instructions: ");
                String usage = scanner.nextLine();
                System.out.print("Notes: ");
                String notes = scanner.nextLine();

                Prescription prescription = new Prescription(
                        medication,
                        dosageType,
                        usage,
                        selectedPatient,
                        (Doctor) currentUser,
                        notes
                );

                selectedPatient.addPrescription(prescription);
                System.out.println("Prescription written successfully!");
            } else {
                System.out.println("Invalid patient selection.");
            }
        }
    }

    private static void updateMedicalRecords() {
        if (currentUser instanceof Doctor) {
            System.out.println("\n=== Update Medical Records ===");
            System.out.print("Patient ID: ");
            String patientId = scanner.nextLine();

            Patient selectedPatient = null;
            for (Person user : users) {
                if (user instanceof Patient && ((Patient) user).getId().equals(patientId)) {
                    selectedPatient = (Patient) user;
                    break;
                }
            }

            if (selectedPatient != null) {
                System.out.print("Blood Type: ");
                String bloodType = scanner.nextLine();
                System.out.print("Height (cm): ");
                double height = Double.parseDouble(scanner.nextLine());
                System.out.print("Weight (kg): ");
                double weight = Double.parseDouble(scanner.nextLine());

                MedicalRecord record = new MedicalRecord(
                        "MR" + System.currentTimeMillis(),
                        selectedPatient,
                        bloodType,
                        height,
                        weight
                );

                selectedPatient.setMedicalRecord(record);
                System.out.println("Medical record updated successfully!");
            } else {
                System.out.println("Invalid patient selection.");
            }
        }
    }

    private static void viewSchedule() {
        if (currentUser instanceof Doctor) {
            System.out.println("\n=== Schedule ===");
            Doctor doctor = (Doctor) currentUser;
            StaticSchedule schedule = doctor.getStaticSchedule();
            for (StaticSchedule.Day day : StaticSchedule.Day.values()) {
                System.out.println("\n" + day + ":");
                List<LocalTime> times = schedule.getAvailableTimeSlots(day, doctor.getAppointments());
                if (times.isEmpty()) {
                    System.out.println("No available slots");
                } else {
                    System.out.println("Available times: " + String.join(", ", times.stream().map(t -> t.toString()).toArray(String[]::new)));
                }
            }
        }
    }

    private static void viewMedicalRecords() {
        if (currentUser instanceof Patient) {
            System.out.println("\n=== Medical Records ===");
            Patient patient = (Patient) currentUser;
            MedicalRecord record = patient.getMedicalRecord();
            if (record != null) {
                System.out.println("Blood Type: " + record.getBloodType());
                System.out.println("Height: " + record.getHeight() + " cm");
                System.out.println("Weight: " + record.getWeight() + " kg");
                System.out.println("\nDiagnoses: " + String.join(", ", record.getDiagnoses()));
                System.out.println("Procedures: " + String.join(", ", record.getProcedures()));
                System.out.println("Medications: " + String.join(", ", record.getMedications()));
                System.out.println("Allergies: " + String.join(", ", record.getAllergies()));
                System.out.println("Immunizations: " + String.join(", ", record.getImmunizations()));
                System.out.println("Lab Results: " + String.join(", ", record.getLabResults()));
                System.out.println("\nNotes: " + String.join("\n", record.getNotes()));
            } else {
                System.out.println("No medical records found.");
            }
        }
    }

    private static void viewPrescriptions() {
        if (currentUser instanceof Patient) {
            System.out.println("\n=== Prescriptions ===");
            Patient patient = (Patient) currentUser;
            List<Prescription> prescriptions = patient.getPrescriptions();
            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found.");
            } else {
                for (Prescription prescription : prescriptions) {
                    System.out.println("\nMedication: " + prescription.getMedication());
                    System.out.println("Dosage Type: " + prescription.getDosageType());
                    System.out.println("Usage: " + prescription.getUsage());
                    System.out.println("Doctor: " + prescription.getDoctor().getFullName());
                    System.out.println("Date: " + prescription.getIssueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    System.out.println("Notes: " + prescription.getNotes());
                }
            }
        } else if (currentUser instanceof Pharmacist) {
            System.out.println("\n=== Prescriptions ===");
            Pharmacist pharmacist = (Pharmacist) currentUser;
            List<Prescription> prescriptions = pharmacist.getPrescriptions();
            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found.");
            } else {
                for (Prescription prescription : prescriptions) {
                    System.out.println("\nMedication: " + prescription.getMedication());
                    System.out.println("Patient: " + prescription.getPatient().getFullName());
                    System.out.println("Doctor: " + prescription.getDoctor().getFullName());
                    System.out.println("Date: " + prescription.getIssueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
        }
    }

    private static void writeReview() {
        if (currentUser instanceof Patient) {
            System.out.println("\n=== Write Review ===");
            System.out.println("\nAvailable Doctors:");
            for (Person user : users) {
                if (user instanceof Doctor) {
                    Doctor doc = (Doctor) user;
                    double avg = doc.calculateAverageRating();
                    System.out.println(doc.getId() + ": " + doc.getFullName() + " (" + doc.getSpecialty() +
                            ") - Average Rating: " + (avg > 0 ? String.format("%.2f/5", avg) : "No reviews yet"));
                }
            }

            System.out.print("\nSelect Doctor ID: ");
            String doctorId = scanner.nextLine();

            Doctor selectedDoctor = null;
            for (Person user : users) {
                if (user instanceof Doctor && ((Doctor) user).getId().equals(doctorId)) {
                    selectedDoctor = (Doctor) user;
                    break;
                }
            }

            if (selectedDoctor != null) {
                System.out.print("Rating (1-5): ");
                int rating = Integer.parseInt(scanner.nextLine());
                System.out.print("Comment: ");
                String comment = scanner.nextLine();

                Review review = new Review((Patient) currentUser, selectedDoctor, comment, rating);
                selectedDoctor.addReview(review);
                System.out.println("Review added successfully!");
            } else {
                System.out.println("Invalid doctor selection.");
            }
        }
    }

    private static void seeReviews() {
        System.out.println("\n=== Doctor Reviews ===");
        for (Person user : users) {
            if (user instanceof Doctor) {
                Doctor doc = (Doctor) user;
                System.out.println("\nDoctor: " + doc.getFullName() + " (" + doc.getSpecialty() + ")");
                List<Review> reviews = doc.getReviews();
                if (reviews.isEmpty()) {
                    System.out.println("No reviews yet.");
                } else {
                    for (Review review : reviews) {
                        System.out.println("- Rating: " + review.getRating() + "/5");
                        System.out.println("  Comment: " + review.getComment());
                    }
                }
            }
        }
    }

    private static void showBilling() {
        if (currentUser instanceof Patient) {
            System.out.println("\n=== Billing ===");
            Patient patient = (Patient) currentUser;
            List<Appointment> appointments = patient.getAppointments();
            double total = 0.0;
            List<Appointment> unpaid = new ArrayList<>();
            if (appointments.isEmpty()) {
                System.out.println("No appointments found.");
            } else {
                for (Appointment apt : appointments) {
                    // Add paid status if not present
                    try {
                        apt.getClass().getDeclaredField("paid");
                    } catch (NoSuchFieldException e) {
                        // Add paid field dynamically is not possible in Java, so assume it's there
                    }
                    boolean isPaid = false;
                    try {
                        isPaid = (boolean) apt.getClass().getMethod("isPaid").invoke(apt);
                    } catch (Exception e) {
                        // If method doesn't exist, treat as unpaid
                    }
                    if (!isPaid) {
                        unpaid.add(apt);
                        System.out.println("\nAppointment ID: " + apt.getAppointmentId());
                        System.out.println("Doctor: " + apt.getDoctor().getFullName());
                        System.out.println("Date: " + apt.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        System.out.println("Cost: $" + apt.getCost());
                        total += apt.getCost();
                    }
                }
                if (unpaid.isEmpty()) {
                    System.out.println("\nAll appointments are paid. No outstanding balance.");
                } else {
                    System.out.println("\nTotal Due: $" + total);
                    System.out.println("1. Pay All");
                    System.out.println("2. Pay by Appointment ID");
                    System.out.println("3. Back");
                    System.out.print("Select an option: ");
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "1":
                            for (Appointment apt : unpaid) {
                                markAppointmentPaid(apt);
                            }
                            System.out.println("All unpaid appointments have been paid.");
                            break;
                        case "2":
                            System.out.print("Enter Appointment ID to pay: ");
                            String id = scanner.nextLine();
                            boolean found = false;
                            for (Appointment apt : unpaid) {
                                if (apt.getAppointmentId().equals(id)) {
                                    markAppointmentPaid(apt);
                                    System.out.println("Appointment " + id + " has been paid.");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Invalid Appointment ID.");
                            }
                            break;
                        case "3":
                            return;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }
    }

    // Helper to mark appointment as paid
    private static void markAppointmentPaid(Appointment apt) {
        try {
            apt.getClass().getMethod("setPaid", boolean.class).invoke(apt, true);
        } catch (Exception e) {
            // If method doesn't exist, do nothing
        }
    }

    private static void createDepartment() {
        if (currentUser instanceof Founder) {
            System.out.println("\n=== Create Department ===");
            System.out.print("Department Name: ");
            String name = scanner.nextLine();
            System.out.print("Location: ");
            String location = scanner.nextLine();

            // Find a doctor to be the head
            System.out.println("\nAvailable Doctors:");
            for (Person user : users) {
                if (user instanceof Doctor) {
                    System.out.println(((Doctor) user).getId() + ": " + ((Doctor) user).getFullName() +
                            " (" + ((Doctor) user).getSpecialty() + ")");
                }
            }
            System.out.print("\nSelect Doctor ID: ");
            String doctorId = scanner.nextLine();

            Doctor selectedDoctor = null;
            for (Person user : users) {
                if (user instanceof Doctor && ((Doctor) user).getId().equals(doctorId)) {
                    selectedDoctor = (Doctor) user;
                    break;
                }
            }

            if (selectedDoctor != null) {
                ((Founder) currentUser).createDepartment(name, selectedDoctor, location);
                System.out.println("Department created successfully!");
            } else {
                System.out.println("Invalid doctor selection.");
            }
        }
    }

    private static void hireDoctor() {
        if (currentUser instanceof Founder) {
            System.out.println("\n=== Hire Doctor ===");
            System.out.print("Doctor ID: ");
            String doctorId = scanner.nextLine();
            System.out.print("Department Name: ");
            String deptName = scanner.nextLine();

            Doctor selectedDoctor = null;
            for (Person user : users) {
                if (user instanceof Doctor && ((Doctor) user).getId().equals(doctorId)) {
                    selectedDoctor = (Doctor) user;
                    break;
                }
            }

            Department selectedDept = null;
            for (Department dept : departments) {
                if (dept.getName().equals(deptName)) {
                    selectedDept = dept;
                    break;
                }
            }

            if (selectedDoctor != null && selectedDept != null) {
                ((Founder) currentUser).hireDoctor(selectedDoctor, selectedDept);
                System.out.println("Doctor hired successfully!");
            } else {
                System.out.println("Invalid doctor or department selection.");
            }
        }
    }

    private static void fireDoctor() {
        if (currentUser instanceof Founder) {
            System.out.println("\n=== Fire Doctor ===");
            System.out.print("Doctor ID: ");
            String doctorId = scanner.nextLine();

            Doctor selectedDoctor = null;
            for (Person user : users) {
                if (user instanceof Doctor && ((Doctor) user).getId().equals(doctorId)) {
                    selectedDoctor = (Doctor) user;
                    break;
                }
            }

            if (selectedDoctor != null) {
                ((Founder) currentUser).fireDoctor(selectedDoctor);
                System.out.println("Doctor fired successfully!");
            } else {
                System.out.println("Invalid doctor selection.");
            }
        }
    }

    private static void viewAllWorkers() {
        if (currentUser instanceof Founder) {
            System.out.println("\n=== All Workers ===");
            System.out.println("\nDoctors:");
            for (Person user : users) {
                if (user instanceof Doctor) {
                    Doctor doctor = (Doctor) user;
                    System.out.println(doctor.getId() + ": " + doctor.getFullName() +
                            " (" + doctor.getSpecialty() + ")");
                }
            }

            System.out.println("\nPharmacists:");
            for (Person user : users) {
                if (user instanceof Pharmacist) {
                    Pharmacist pharmacist = (Pharmacist) user;
                    System.out.println(pharmacist.getId() + ": " + pharmacist.getFullName() +
                            " (" + pharmacist.getLocation() + ")");
                }
            }
        }
    }

    private static void viewAllDepartments() {
        if (currentUser instanceof Founder) {
            System.out.println("\n=== All Departments ===");
            for (Department dept : departments) {
                System.out.println("\nDepartment: " + dept.getName());
                System.out.println("Location: " + dept.getLocation());
                System.out.println("Head: " + (dept.getHead() != null ? dept.getHead().getFullName() : "None"));
                System.out.println("Doctors:");
                for (Doctor doctor : dept.getDoctors()) {
                    System.out.println("  - " + doctor.getFullName() + " (" + doctor.getSpecialty() + ")");
                }
            }
        }
    }

    private static void generateMonthlyReport() {
        if (currentUser instanceof Founder) {
            System.out.println("\n=== Monthly Report ===");
            System.out.println("Departments: " + departments.size());
            int doctorCount = 0;
            int patientCount = 0;
            int pharmacistCount = 0;
            int appointmentCount = 0;
            double totalRevenue = 0.0;
            double totalExpenses = 0.0;
            for (Person user : users) {
                if (user instanceof Doctor) doctorCount++;
                if (user instanceof Patient) patientCount++;
                if (user instanceof Pharmacist) pharmacistCount++;
            }
            for (Person user : users) {
                if (user instanceof Doctor) {
                    Doctor doc = (Doctor) user;
                    for (Appointment apt : doc.getAppointments()) {
                        appointmentCount++;
                        totalRevenue += apt.getCost();
                    }
                    totalExpenses += ((Doctor) user).getSalary();
                }
                if (user instanceof Pharmacist) {
                    totalExpenses += ((Pharmacist) user).getSalary();
                }
            }
            System.out.println("Doctors: " + doctorCount);
            System.out.println("Patients: " + patientCount);
            System.out.println("Pharmacists: " + pharmacistCount);
            System.out.println("Appointments: " + appointmentCount);
            System.out.println("Total Revenue: $" + totalRevenue);
            System.out.println("Total Expenses: $" + totalExpenses);
            System.out.println("Net Income: $" + (totalRevenue - totalExpenses));
        }
    }

    private static void manageInventory() {
        if (currentUser instanceof Pharmacist) {
            System.out.println("\n=== Manage Inventory ===");
            System.out.println("Inventory management functionality to be implemented.");
        }
    }

    private static void manageDoctorAppointments() {
        if (currentUser instanceof Doctor) {
            Doctor doctor = (Doctor) currentUser;
            List<Appointment> appointments = doctor.getAppointments();
            if (appointments.isEmpty()) {
                System.out.println("No appointments to manage.");
                return;
            }
            System.out.println("\n=== Manage Appointments ===");
            for (Appointment apt : appointments) {
                System.out.println("\nAppointment ID: " + apt.getAppointmentId());
                System.out.println("Patient: " + apt.getPatient().getFullName());
                System.out.println("Date: " + apt.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                System.out.println("Status: " + apt.getStatus());
            }
            System.out.print("\nEnter Appointment ID to manage (or 'back' to return): ");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("back")) return;
            Appointment selected = null;
            for (Appointment apt : appointments) {
                if (apt.getAppointmentId().equals(id)) {
                    selected = apt;
                    break;
                }
            }
            if (selected == null) {
                System.out.println("Invalid Appointment ID.");
                return;
            }
            System.out.println("1. Mark as Completed");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    setAppointmentStatus(selected, "Completed");
                    System.out.println("Appointment marked as completed.");
                    break;
                case "2":
                    setAppointmentStatus(selected, "Canceled");
                    System.out.println("Appointment canceled.");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Helper to set appointment status
    private static void setAppointmentStatus(Appointment apt, String status) {
        try {
            apt.getClass().getMethod("setStatus", String.class).invoke(apt, status);
        } catch (Exception e) {
            // If method doesn't exist, do nothing
        }
    }

    private static void createDoctor() {
        System.out.println("\n=== Create Doctor ===");
        String doctorId = "D" + String.format("%03d", users.stream().filter(u -> u instanceof Doctor).count() + 1);
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender (M/F): ");
        char gender = scanner.nextLine().toUpperCase().charAt(0);
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();
        System.out.print("Office Number: ");
        String officeNumber = scanner.nextLine();
        System.out.print("Max Patients Per Day: ");
        short maxPatients = Short.parseShort(scanner.nextLine());
        System.out.print("Is Private Doctor? (yes/no): ");
        boolean isPrivate = scanner.nextLine().toLowerCase().startsWith("y");
        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        Doctor newDoctor = new Doctor(
                doctorId,
                firstName,
                lastName,
                age,
                gender,
                phoneNumber,
                username,
                password,
                department,
                specialty,
                officeNumber,
                maxPatients,
                isPrivate,
                salary
        );
        newDoctor.setStaticSchedule(new StaticSchedule());
        users.add(newDoctor);
        System.out.println("Doctor created successfully!");
    }

    private static void createAssistant() {
        System.out.println("\n=== Create Assistant ===");
        String assistantId = "A" + String.format("%03d", users.stream().filter(u -> u instanceof Assistant).count() + 1);
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender (M/F): ");
        char gender = scanner.nextLine().toUpperCase().charAt(0);
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Select supervisor
        System.out.println("Available Doctors:");
        for (Person user : users) {
            if (user instanceof Doctor) {
                Doctor doc = (Doctor) user;
                System.out.println(doc.getId() + ": " + doc.getFullName());
            }
        }
        System.out.print("Supervisor Doctor ID: ");
        String supervisorId = scanner.nextLine();
        Doctor supervisor = null;
        for (Person user : users) {
            if (user instanceof Doctor && ((Doctor) user).getId().equals(supervisorId)) {
                supervisor = (Doctor) user;
                break;
            }
        }
        if (supervisor == null) {
            System.out.println("Invalid supervisor. Assistant not created.");
            return;
        }

        System.out.print("Experience (years): ");
        short experience = Short.parseShort(scanner.nextLine());
        System.out.print("Duty: ");
        String duty = scanner.nextLine();

        // Select department
        System.out.println("Available Departments:");
        for (Department dept : departments) {
            System.out.println(dept.getName());
        }
        System.out.print("Department Name: ");
        String deptName = scanner.nextLine();
        Department department = null;
        for (Department dept : departments) {
            if (dept.getName().equals(deptName)) {
                department = dept;
                break;
            }
        }
        if (department == null) {
            System.out.println("Invalid department. Assistant not created.");
            return;
        }

        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("Work Schedule: ");
        String workSchedule = scanner.nextLine();

        Assistant newAssistant = new Assistant(
                assistantId, firstName, lastName, age, gender, phoneNumber, username, password,
                supervisor, experience, duty, department, salary, workSchedule
        );
        users.add(newAssistant);
        System.out.println("Assistant created successfully!");
    }
}