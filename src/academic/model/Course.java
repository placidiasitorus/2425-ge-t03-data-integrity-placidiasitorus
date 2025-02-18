package academic.model;

/**
 * @author 12s23022 P. Santa Hillary Sitorus
 * @author 12s23034 Pariama Valentino Sihombing
 */

public class Course {
    private String code;
    private String name;
    private int credits;
    private String grade;

    public Course(String code, String name, int credits, String grade) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + "|" + name + "|" + credits + "|" + grade;
    }
}
