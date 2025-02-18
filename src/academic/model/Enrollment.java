package academic.model;


/**
 * @author 12s23022 P. Santa Hillary Sitorus
 * @author 12s23034 Pariama Valentino Sihombing
 */
public class Enrollment {

    private String courseCode;
    private String studentId;
    private String academicYear;
    private String semester;
    private String grade;

   
    public Enrollment(String courseCode, String studentId, String academicYear, String semester) {
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester;
        this.grade = "None"; 
    }


    public String getCourseCode() { return courseCode; }
    public String getStudentId() { return studentId; }
    public String getAcademicYear() { return academicYear; }
    public String getSemester() { return semester; }
    public String getGrade() { return grade; }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return courseCode + "|" + studentId + "|" + academicYear + "|" + semester + "|" + grade;
    }
}