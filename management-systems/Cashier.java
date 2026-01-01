package POO1;

class Cashier extends StoreEmployee {
    private int receiptsCount;
    private String schedule;

    public Cashier(int id, String lastName, String firstName, String phone, String address,
                   int salary, int experience, int receiptsCount, String schedule) {
        super(id, lastName, firstName, phone, address, salary, experience);
        this.receiptsCount = receiptsCount;
        this.schedule = schedule;
    }

    @Override
    public void generalInfo() {
        System.out.println("Cashier: " + getLastName() + " " + getFirstName() + " Id " + getId() +
                " Phone " + getPhone() + " Address " + getAddress() + " Receipts " +
                receiptsCount + " Schedule " + schedule + " Experience " + getExperience());

    }

    @Override
    public int calculateSalary() {
        return salary + (receiptsCount * 2); // Bonus of 2 per receipt
    }
}
