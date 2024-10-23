package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO implements Serializable {
    
    // <editor-fold desc="Fields">

    private String username;

    private String password;

    private String name;

    private String email;

    private long courseCode;

    private List<SubjectDTO> subjects;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    @SuppressWarnings("unused")
    public StudentDTO() {
        this.subjects = new ArrayList<>();
    }

    public StudentDTO(String username, String password, String name, String email, long courseCode) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.courseCode = courseCode;
        this.subjects = new ArrayList<>();
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    // </editor-fold>

    // <editor-fold desc="Methods">

    // Converts an entity Student to a DTO Student class
    public static StudentDTO from(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getPassword(),
                student.getName(),
                student.getEmail(),
                student.getCourse().getCode()
        );
    }

    // Converts an entire list of entities into a list of DTOs
    public static List<StudentDTO> from(List<Student> students) {
        return students.stream().map(StudentDTO::from).collect(Collectors.toList());
    }

    // </editor-fold>
}
