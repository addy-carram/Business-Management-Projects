package POO1;

class Manager extends StoreEmployee {
    private int subordinates;
    private String department;

    public Manager(int id, String lastName, String firstName, String phone, String address,
                   int salary, int experience, int subordinates, String department) {
        super(id, lastName, firstName, phone, address, salary, experience);
        this.subordinates = subordinates;
        this.department = department;
    }

    public int getSubordinates(){ return subordinates; }

    public String getDepartment(){ return department; }

    @Override
    public void generalInfo() {
        System.out.println("Manager: " + getLastName() + " " + getFirstName() + " Id " + getId() +
                " Phone " + getPhone() + " Address " + getAddress() + " Department: " +
                getDepartment() + ", Subordinates: " + getSubordinates() + " Experience " + getExperience());
    }

    @Override
    public int calculateSalary() {
        return salary + (subordinates * 50); // Bonus of 50 per subordinate
    }
}
