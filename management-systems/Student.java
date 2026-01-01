package POO1;

class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String address;
    private int phone;
    private String major;
    private int year;
    private String group;
    private String faculty;

    Student(int id, String lastName, String firstName,
            String middleName, String birthDate, String address,
            int phone, String major, int year, String group, String faculty) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.major = major;
        this.year = year;
        this.group = group;
        this.faculty = faculty;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public String getLastName() { return lastName; }

    public String getGroup() { return group; }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + middleName + " " + birthDate + " " + address + " " + phone + " " +
                major + " " + year + " " + group + " " + faculty;
    }
}
