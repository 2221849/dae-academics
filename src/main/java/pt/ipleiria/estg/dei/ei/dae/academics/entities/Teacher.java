package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends User {

    // <editor-fold desc="Fields">

    private String office;

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    public Teacher() {
    }

    public Teacher(String username, String password, String name, String email, String office) {
        super(username, password, name, email);
        this.office = office;
        this.subjects = new ArrayList<>();
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    // </editor-fold>
}