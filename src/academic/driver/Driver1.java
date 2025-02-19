package academic.driver;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                        } else {
                            System.out.println("Duplicate course detected: " + code);
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
                        } else {
                            System.out.println("Duplicate student detected: " + id);
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
                        } else {
                            System.out.println("Duplicate enrollment detected: " + courseCode + " - " + studentId);
                        }
                    }
                    break;
            }
        }

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

    private static boolean isEnrollmentExists(List<Enrollment> enrollments, String courseCode, String studentId, String academicYear, String semester) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseCode().equals(courseCode) &&
                enrollment.getStudentId().equals(studentId) &&
                enrollment.getAcademicYear().equals(academicYear) &&
                enrollment.getSemester().equals(semester)) {
                return true;
            }
        } 
        return false;
    }  
}