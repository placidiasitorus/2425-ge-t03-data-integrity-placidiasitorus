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
        Set<String> courseCodes = new HashSet<>();
        Set<String> studentIds = new HashSet<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("---")) {
                break;
            }

            String[] parts = input.split("#");
            if (parts.length < 2) continue; // Skip jika format tidak sesuai
            
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
                            errors.add("invalid course credits|" + parts[3]);
                            break;
                        }
                        String grade = parts[4];

                        if (!courseCodes.contains(code)) {
                            courses.add(new Course(code, name, credits, grade));
                            courseCodes.add(code);
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
                            errors.add("invalid student year|" + parts[3]);
                            break;
                        }
                        String major = parts[4];

                        if (!studentIds.contains(id)) {
                            students.add(new Student(id, name, year, major));
                            studentIds.add(id);
                        }
                    }
                    break;

                case "enrollment-add":
                    if (parts.length == 5) {
                        String courseCode = parts[1];
                        String studentId = parts[2];
                        String academicYear = parts[3];
                        String semester = parts[4];

                        if (!courseCodes.contains(courseCode)) {
                            errors.add("invalid course|" + courseCode);
                        } else if (!studentIds.contains(studentId)) {
                            errors.add("invalid student|" + studentId);
                        } else {
                            enrollments.add(new Enrollment(courseCode, studentId, academicYear, semester));
                        }
                    }
                    break;
            }
        }

        errors.forEach(System.out::println);

        courses.sort(Comparator.comparing(Course::getCode));
        courses.forEach(System.out::println);

        students.sort(Comparator.comparing(Student::getId));
        students.forEach(System.out::println);

        enrollments.forEach(System.out::println);

        scanner.close();
    }
}
