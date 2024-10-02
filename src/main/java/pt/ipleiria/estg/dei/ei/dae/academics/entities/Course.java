package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "courses",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        }
)
@NamedQueries(
        @NamedQuery(
                name = "getAllCourses",
                query = "SELECT c FROM Course c ORDER BY c.code" // JPQL
        )
)
public class Course {

    // <editor-fold desc="Fields">
    @Id
    private long code;
    @NotNull
    private String name;
    @OneToMany(mappedBy = "course")
    private List<Student> students;
    // </editor-fold>

    // <editor-fold desc="Constructors">
    public Course(long code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public Course() {
        students = new ArrayList<>();
    }
    // </editor-fold>

    // <editor-fold desc="Getters"> and Setters
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    // </editor-fold>

    // <editor-fold desc="Methods">
    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
    // </editor-fold>
}
