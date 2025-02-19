package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.*;

/**
 * @autor 12S23034 Pariama Valentino
 * @autor 12S23022 P. Santa Hillary Sitorus
 */

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }

            String[] parts = input.split("#");
            String command = parts[0];

            switch (command) {
                case "course-add":
                    if (parts.length == 5) {
                        String code = parts[1];
                        String name = parts[2];
                        int credits = Integer.parseInt(parts[3]);
                        String grade = parts[4];

                        if (!isCourseExists(courses, code)) {
                            courses.add(new Course(code, name, credits, grade));
                        }
                    }
                    break;

                case "student-add":
                    if (parts.length == 5) {
                        String id = parts[1];
                        String name = parts[2];
                        int year = Integer.parseInt(parts[3]);
                        String major = parts[4];

                        // Jangan urutkan daftar mahasiswa, biarkan sesuai urutan input
                        students.add(new Student(id, name, year, major));
                    }
                    break;

                case "enrollment-add":
                    if (parts.length == 5) {
                        String courseCode = parts[1];
                        String studentId = parts[2];
                        String academicYear = parts[3];
                        String semester = parts[4];

                        if (!isCourseExists(courses, courseCode)) {
                            errors.add("invalid course|" + courseCode);
                        } else if (!isStudentExists(students, studentId)) {
                            errors.add("invalid student|" + studentId);
                        } else {
                            enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                        }
                    }
                    break;
            }
        }

        for (String error : errors) {
            System.out.println(error);
        }

        // Tetap urutkan daftar course
        courses.sort(Comparator.comparing(Course::getCode));
        for (Course course : courses) {
            System.out.println(course);
        }

        // Jangan urutkan daftar mahasiswa agar sesuai input
        for (Student student : students) {
            System.out.println(student);
        }

        // Urutkan enrollments berdasarkan Course Code lalu Student ID
        enrollments.sort(Comparator.comparing(Enrollment::getCourseCode)
                                   .thenComparing(Enrollment::getStudentId));
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        scanner.close();
    }

    private static boolean isCourseExists(List<Course> courses, String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStudentExists(List<Student> students, String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}