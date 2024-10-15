package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO implements Serializable {

    // <editor-fold desc="Fields">

    private long code;

    private String name;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    @SuppressWarnings("unused")
    public CourseDTO() {
    }

    public CourseDTO(long code, String name) {
        this.code = code;
        this.name = name;
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

    // </editor-fold>

    // <editor-fold desc="Methods">

    public static CourseDTO from(Course course) {
        return new CourseDTO(
                course.getCode(),
                course.getName()
        );
    }

    public static List<CourseDTO> from(List<Course> courses) {
        return courses.stream().map(CourseDTO::from).collect(Collectors.toList());
    }

    // </editor-fold>
}
