package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "subjects",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "course_code", "school_year"})
        }
)
@NamedQueries(
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s " +
                        "JOIN Course c " +
                        "ORDER BY c.name, s.schoolYear DESC, s.name" // JPQL
        )
)
public class Subject {

    // <editor-fold desc="Fields">

    @Id
    private long code;

    private String name;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "course_year")
    private int courseYear;

    @NotNull
    @ManyToOne
    private Course course;

    @ManyToMany()
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_username",
                    referencedColumnName = "username"
            )
    )
    private List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "subject_teacher", // Name of the join table
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_username",
                    referencedColumnName = "username"
            )
    )
    private List<Teacher> teachers;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    public Subject() {
    }

    public Subject(long code, String name, String schoolYear, int courseYear, Course course) {
        this.code = code;
        this.name = name;
        this.schoolYear = schoolYear;
        this.courseYear = courseYear;
        this.course = course;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
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

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    // </editor-fold>

    // <editor-fold desc="Methods">

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    // </editor-fold>
}
