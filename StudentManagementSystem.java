
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    static class Student {
        int id;
        String name;
        String course;
        double marks;

        Student(int id, String name, String course, double marks) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.marks = marks;
        }

        public String toString() {
            return "ID: " + id +
                   " | Name: " + name +
                   " | Course: " + course +
                   " | Marks: " + marks;
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("Thank you!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        System.out.print("Enter marks: ");
        try {
            double marks = Double.parseDouble(sc.nextLine());

            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }

            students.add(new Student(nextId++, name, course, marks));
            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid marks.");
        }
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    static void searchStudent() {
        System.out.print("Enter student ID: ");
        int id = readId();
        if (id == -1) return;

        Student student = findStudent(id);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = readId();
        if (id == -1) return;

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        student.name = sc.nextLine();

        System.out.print("Enter new course: ");
        student.course = sc.nextLine();

        System.out.print("Enter new marks: ");
        try {
            double marks = Double.parseDouble(sc.nextLine());

            if (marks < 0 || marks > 100) {
                System.out.println("Marks must be between 0 and 100.");
                return;
            }

            student.marks = marks;
            System.out.println("Student updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid marks.");
        }
    }

    static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = readId();
        if (id == -1) return;

        Student student = findStudent(id);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    static Student findStudent(int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }
        return null;
    }

    static int readId() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid ID.");
            return -1;
        }
    }
}
