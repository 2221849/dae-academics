package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllStudents",
                query = "SELECT s FROM Student s ORDER BY s.name" // JPQL
        )
})
public class Student extends User implements Serializable{

    // <editor-fold desc="Fields">

    @ManyToOne
    @NotNull
    private Course course;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    public Student() {
    }

    public Student(String username, String password, String name, String email, Course course) {
        super(username, password, name, email);
        this.course = course;
        this.subjects = new ArrayList<>();
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    // </editor-fold>

    // <editor-fold desc="Methods">

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    // </editor-fold>
}
