package objects;

public abstract class Person {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private char gender;
    private String phoneNumber;
    private String username;
    private String password;

    public Person(String id, String firstName, String lastName, int age, char gender, 
                 String phoneNumber, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Authentication methods
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public abstract String GeneralInfo();
}
