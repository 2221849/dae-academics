package pt.ipleiria.estg.dei.ei.dae.academics.dtos;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectDTO implements Serializable {

    // <editor-fold desc="Fields">

    private long code;

    private String name;

    private String scholarYear;

    private int courseYear;

    private long courseCode;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    @SuppressWarnings("unused")
    public SubjectDTO() {
    }

    public SubjectDTO(long code, String name, String scholarYear, int courseYear, long courseCode) {
        this.code = code;
        this.name = name;
        this.scholarYear = scholarYear;
        this.courseYear = courseYear;
        this.courseCode = courseCode;
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

    public String getScholarYear() {
        return scholarYear;
    }

    public void setScholarYear(String scholarYear) {
        this.scholarYear = scholarYear;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }

    // </editor-fold>

    // <editor-fold desc="Methods">

    public static SubjectDTO from(Subject subject) {
        return new SubjectDTO(
                subject.getCode(),
                subject.getName(),
                subject.getSchoolYear(),
                subject.getCourseYear(),
                subject.getCourse().getCode()
        );
    }

    public static List<SubjectDTO> from(List<Subject> subjects) {
        return subjects.stream().map(SubjectDTO::from).collect(Collectors.toList());
    }

    // </editor-fold>
}
