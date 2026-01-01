package POO1;

abstract class StoreEmployee {
    private int id;
    private String lastName;
    private String firstName;
    private String phone;
    private String address;
    protected int salary;
    protected int experience;

    // Constructor
    public StoreEmployee(int id, String lastName, String firstName, String phone,
                         String address, int salary, int experience) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.experience = experience;
    }

    // Getters and setters
    public int getId() { return id; }

    public String getLastName() { return lastName; }

    public String getFirstName() { return firstName; }

    public String getPhone() { return phone; }

    public String getAddress() { return address; }

    public int getSalary() { return salary; }

    public int getExperience() { return experience; }

    public void setSalary(int salary) { this.salary = salary; }

    public void setExperience(int experience) { this.experience = experience; }

    // Abstract methods
    public abstract void generalInfo();

    public abstract int calculateSalary();
}
