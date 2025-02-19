package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

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

                        if (!isEnrollmentExists(enrollments, courseCode, studentId, academicYear, semester)) {
                            enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                        }
                    }
                    break;
            }
        }

        courses.sort(Comparator.comparing(Course::getCode));
        students.sort(Comparator.comparing(Student::getId));
        enrollments.sort(Comparator.comparing(Enrollment::getCourseCode).thenComparing(Enrollment::getStudentId));

        for (Course course : courses) {
            System.out.println(course);
        }

        for (Student student : students) {
            System.out.println(student);
        }

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

    private static boolean isEnrollmentExists(List<Enrollment> enrollments, String courseCode, String studentId, String academicYear, String semester) {
        return enrollments.stream().anyMatch(enrollment ->
                enrollment.getCourseCode().equals(courseCode) &&
                        enrollment.getStudentId().equals(studentId) &&
                        enrollment.getAcademicYear().equals(academicYear) &&
                        enrollment.getSemester().equals(semester));
    }
}
 