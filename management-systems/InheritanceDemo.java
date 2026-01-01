package POO1;

import java.util.ArrayList;
import java.util.Scanner;

// Main class for testing (Inheritance demo)
public class InheritanceDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<StoreEmployee> employeeList = new ArrayList<>();
        int option;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add employee");
            System.out.println("2. Remove employee");
            System.out.println("3. Show employee list");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    addEmployee(employeeList, scanner);
                    break;
                case 2:
                    removeEmployee(employeeList, scanner);
                    break;
                case 3:
                    showEmployees(employeeList);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        } while (option != 4);

        scanner.close();
    }

    public static void addEmployee(ArrayList<StoreEmployee> list, Scanner scanner) {
        System.out.println("\nChoose employee type:");
        System.out.println("1. Manager");
        System.out.println("2. Cashier");
        System.out.println("3. Consultant");
        System.out.print("Option: ");
        int type = scanner.nextInt();

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Salary: ");
        int salary = scanner.nextInt();

        System.out.print("Experience (years): ");
        int experience = scanner.nextInt();

        if(id>0 && phone.length()==9 && salary>0 && experience>0){
        switch (type) {
            case 1:
                System.out.print("Number of subordinates: ");
                int subordinates = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Department: ");
                String department = scanner.nextLine();
                list.add(new Manager(id, lastName, firstName, phone, address, salary, experience, subordinates, department));
                break;
            case 2:
                System.out.print("Number of receipts: ");
                int receipts = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Schedule: ");
                String schedule = scanner.nextLine();
                list.add(new Cashier(id, lastName, firstName, phone, address, salary, experience, receipts, schedule));
                break;
            case 3:
                System.out.print("Number of sales: ");
                int sales = scanner.nextInt();
                System.out.print("Number of clients: ");
                int clientCount = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Field: ");
                String field = scanner.nextLine();
                list.add(new Consultant(id, lastName, firstName, phone, address, salary, experience, sales, clientCount, field));
                break;
            default:
                System.out.println("Invalid type!");
        }

        System.out.println("Employee added successfully!");
    }
    else System.out.println("You entered unacceptable data");}

     public static void removeEmployee(ArrayList<StoreEmployee> list, Scanner scanner) {
        System.out.print("\nEnter the ID of the employee to remove: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (StoreEmployee emp : list) {
            if (emp.getId() == id) {
                list.remove(emp);
                System.out.println("Employee removed!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Employee with ID " + id + " was not found.");
        }
    }

    public static void showEmployees(ArrayList<StoreEmployee> list) {
        if (list.isEmpty()) {
            System.out.println("\nEmployee list is empty.");
        } else {
            System.out.println("\nEmployee list:");
            for (StoreEmployee emp : list) {
                emp.generalInfo();
            }
        }
    }
}
