package POO1;


class Consultant extends StoreEmployee {
    private int sales;
    private int clientCount;
    private String field;

    public Consultant(int id, String lastName, String firstName, String phone, String address,
                      int salary, int experience, int sales, int clientCount, String field) {
        super(id, lastName, firstName, phone, address, salary, experience);
        this.sales = sales;
        this.clientCount = clientCount;
        this.field = field;
    }

    @Override
    public void generalInfo() {
        System.out.println("Consultant " + getLastName() + " " + getFirstName() + " Id " + getId() +
                " Phone " + getPhone() + " Address " + getAddress() + " Clients "
                + clientCount + " Sales " + sales + " Field " + field + " Experience " + getExperience());

    }

    @Override
    public int calculateSalary() {
        return salary + (sales * 10); // Bonus of 10 per sale
    }
}
