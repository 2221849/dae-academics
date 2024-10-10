package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectDTO {

    // <editor-fold desc="Fields">
    private long code;
    private String name;
    private long courseCode;
    private String courseName;
    private int courseYear;
    private String scholarYear;
    // </editor-fold>

    // <editor-fold desc="Constructors">
    public SubjectDTO() {
    }

    public SubjectDTO(long code, String name, long courseCode, String courseName, int courseYear, String scholarYear) {
        this.code = code;
        this.name = name;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.scholarYear = scholarYear;
    }
    // </editor-fold>

    // <editor-fold desc="Getters and Setters">
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getScholarYear() {
        return scholarYear;
    }

    public void setScholarYear(String scholarYear) {
        this.scholarYear = scholarYear;
    }
    // </editor-fold>

    // <editor-fold desc="Methods">
    public static SubjectDTO from(Subject subject) {
        return new SubjectDTO(
                subject.getCode(),
                subject.getName(),
                subject.getCourse().getCode(),
                subject.getCourse().getName(),
                subject.getCourseYear(),
                subject.getSchoolYear()
        );
    }

    public static List<SubjectDTO> from(List<Subject> subjects) {
        return subjects.stream().map(SubjectDTO::from).collect(Collectors.toList());
    }
    // </editor-fold>
}
