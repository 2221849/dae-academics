package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherDTO {

    // <editor-fold desc="Fields">

    private String username;

    private String password;

    private String name;

    private String email;

    private String office;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    @SuppressWarnings("unused")
    public TeacherDTO() {
    }

    public TeacherDTO(String username, String password, String name, String email, String office) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.office = office;
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

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    // </editor-fold>

    // <editor-fold desc="Methods">

    public static TeacherDTO from(Teacher teacher) {
        return new TeacherDTO(
                teacher.getUsername(),
                teacher.getPassword(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getOffice()
        );
    }

    public static List<TeacherDTO> from(List<Teacher> teachers) {
        return teachers.stream().map(TeacherDTO::from).collect(Collectors.toList());
    }

    // </editor-fold>

}
