package academic.model;

/**
 * @author 12s23022 P. Santa Hillary Sitorus
 * @author 12s23034 Pariama Valentino Sihombing
 */

public class Student { 
    private String id;
    private String name;
    private int year;
    private String major;

    public Student(String id, String name, int year, String major) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + year + "|" + major;
    }
}
