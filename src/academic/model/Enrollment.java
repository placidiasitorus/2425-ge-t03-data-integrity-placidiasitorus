package academic.model;

/**
 * @author 12s23022 P. Santa Hillary Sitorus
 * @author 12s23034 Pariama Valentino Sihombing
 */

public class Enrollment {
    private String studentId;
    private String academicYear;
    private String semester; 
    private String courseCode;
 
    public Enrollment(String courseCode, String studentId, String academicYear, String semester) {
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester; 
    } 

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return courseCode + "|" + studentId + "|" + academicYear + "|" + semester + "|None";
    }
}