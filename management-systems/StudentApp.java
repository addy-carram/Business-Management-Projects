package POO1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;


public class StudentApp {
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);

        Student[] students = new Student[100];
        try (BufferedReader br = new BufferedReader(new FileReader("student.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");

                if (parts.length >= 11) {
                    int id = Integer.parseInt(parts[0]);
                    String lastName = parts[1];
                    String firstName = parts[2];
                    String middleName = parts[3];
                    String birthDate = parts[4];
                    String address = parts[5];
                    int phone = Integer.parseInt(parts[6]);
                    String major = parts[7];
                    int year = Integer.parseInt(parts[8]);
                    String group = parts[9];
                    String faculty = parts[10];
                    students[i] = new Student(id, lastName, firstName,
                            middleName, birthDate, address,
                            phone, major, year, group, faculty);
                    i++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        try{
        int op = 1;
        while (op != 0) {
            System.out.println("Menu:");
            System.out.println("1 Add a new student");
            System.out.println("2 Show all students");
            System.out.println("3 Show students by faculty");
            System.out.println("4 Show students sorted by name");
            System.out.println("5 Show students sorted by group");
            System.out.println("6 Show number of students in a given major");
            System.out.println("7 Show students for faculty and major");
            System.out.println("0 Exit");
            System.out.print("Choose an option: ");



            op = r.nextInt();
            r.nextLine(); // consume remaining newline after nextInt()

            switch (op) {
                case 1: {
                    System.out.println("Enter a record in order: id lastName firstName middleName birthDate address phone major year group faculty");
                    String studentLine = r.nextLine();
                    String[] parts = studentLine.trim().split("\\s+");

                    if (parts.length >= 11) {
                        int id = Integer.parseInt(parts[0]);
                        String lastName = parts[1];
                        String firstName = parts[2];
                        String middleName = parts[3];
                        String birthDate = parts[4];
                        String address = parts[5];
                        int phone = Integer.parseInt(parts[6]);
                        String major = parts[7];
                        int year = Integer.parseInt(parts[8]);
                        String group = parts[9];
                        String faculty = parts[10];

                        if(id>0 && year>0 && year<=4) {
                            boolean added = false;
                            for (int i = 0; i < students.length; i++) {
                                if (students[i] == null) {
                                    students[i] = new Student(id, lastName, firstName, middleName, birthDate, address, phone, major, year, group, faculty);
                                    System.out.println("Added successfully!");
                                    added = true;
                                    break;
                                }
                            }
                            if (!added) {
                                System.out.println("Error: No available slots.");
                            }
                        }
                        else System.out.println(" You entered negative data");

                    } else {
                        System.out.println("Error: Insufficient data!");
                    }

                    // Print updated students list
                    System.out.println("Updated student list:");
                    for (Student student : students) {
                        if (student != null) {
                            System.out.println(student);
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println("Showing students:");
                    for (Student student : students) {
                        if (student != null) {
                            System.out.println(student);
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter faculty:");
                    String faculty = r.nextLine();
                    for (int i = 0; i < students.length; i++) {
                        if (students[i]!=null && faculty.equalsIgnoreCase(students[i].toString().split(" ")[10])) {
                            System.out.println(students[i]);
                        }
                    }
                } break;

                case 4: {
                    // Sort by last name (bubble sort)
                    for (int j = 0; j < students.length - 1; j++) {
                        for (int k = 0; k < students.length - 1 - j; k++) {
                            if (students[k] != null && students[k + 1] != null) {
                                if (students[k].getLastName().compareTo(students[k + 1].getLastName()) > 0) {
                                    Student temp = students[k];
                                    students[k] = students[k + 1];
                                    students[k + 1] = temp;
                                }
                            }
                        }
                    }

                    System.out.println("Students list sorted by last name:");
                    for (Student student : students) {
                        if (student != null) {
                            System.out.println(student);
                        }
                    }
                    break;
                }
                case 5: {
                    // Sort by group (bubble sort)
                    for (int j = 0; j < students.length - 1; j++) {
                        for (int k = 0; k < students.length - 1 - j; k++) {
                            if (students[k] != null && students[k + 1] != null) {
                                if (students[k].getGroup().compareTo(students[k + 1].getGroup()) > 0) {
                                    Student temp = students[k];
                                    students[k] = students[k + 1];
                                    students[k + 1] = temp;
                                }
                            }
                        }
                    }

                    System.out.println("Students list sorted by group:");
                    for (Student student : students) {
                        if (student != null) {
                            System.out.println(student);
                        }
                    }
                    break;
                }
                case 6:  {
                    System.out.println("Enter major:");
                    String major= r.nextLine();

                    for (int i = 0; i < students.length; i++) {
                        if (students[i]!=null && major.equalsIgnoreCase(students[i].toString().split(" ")[7])) {
                            System.out.println(students[i]);
                        }
                    }

                }break;
                case 7:{
                    System.out.println("Enter faculty and major:");
                    String faculty = r.nextLine();
                    String major=r.nextLine();
                    for (int i = 0; i < students.length; i++) {
                        if (students[i]!=null && faculty.equalsIgnoreCase(students[i].toString().split(" ")[10]) && major.equals(students[i].toString().split(" ")[7] )) {
                            System.out.println(students[i]);
                        }
                    }

                }break;


                case 0:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

        r.close();}
     catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Error: Array index out of bounds!");
    } catch (
    InputMismatchException e) {
        System.out.println("Error: Enter a valid number!");
        r.nextLine(); // Clear buffer to avoid infinite loops
    } catch (NumberFormatException e) {
        System.out.println("Error: Invalid number format!");
    } catch (NullPointerException e) {
        System.out.println("Error: Attempt to access a null object!");
    } catch (Exception e) { // General catch for unexpected exceptions
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }
    }


}
