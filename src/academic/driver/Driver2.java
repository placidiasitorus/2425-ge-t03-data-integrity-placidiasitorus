package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.*;

/**
 * @autor 12S23034 Pariama Valentino
 * @autor 12S23022 P. Santa Hillary Sitorus
 */

public class Driver2 {
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
                        int credits;
                        try {
                            credits = Integer.parseInt(parts[3]);
                        } catch (NumberFormatException e) {
                            continue; // Skip invalid input
                        }
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
                        int year;
                        try {
                            year = Integer.parseInt(parts[3]);
                        } catch (NumberFormatException e) {
                            continue; // Skip invalid input
                        }
                        String major = parts[4];

                        if (!isStudentExists(students, id)) {
                            students.add(new Student(id, name, year, major));
                        }
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

        courses.sort(Comparator.comparing(Course::getCode));
        for (Course course : courses) {
            System.out.println(course);
        }

        students.sort(Comparator.comparing(Student::getId)); // Urutkan mahasiswa sebelum mencetak
        for (Student student : students) {
            System.out.println(student);
        }

        enrollments.sort(Comparator.comparing(Enrollment::getStudentId)); // Urutkan Enrollment berdasarkan ID Mahasiswa
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        scanner.close();
    }

    private static boolean isCourseExists(List<Course> courses, String code) {
        return courses.stream().anyMatch(course -> course.getCode().equals(code));
    }

    private static boolean isStudentExists(List<Student> students, String id) {
        return students.stream().anyMatch(student -> student.getId().equals(id));
    }
}