package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class AdministratorDTO implements Serializable {

    // <editor-fold desc="Fields">

    private String username;

    private String password;

    private String name;

    private String email;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    @SuppressWarnings("unused")
    public AdministratorDTO() {
    }

    public AdministratorDTO(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public static AdministratorDTO from(Administrator administrator) {
        return new AdministratorDTO(
                administrator.getUsername(),
                administrator.getPassword(),
                administrator.getName(),
                administrator.getEmail()
        );
    }

    public static List<AdministratorDTO> from(List<Administrator> administrators) {
        return administrators.stream().map(AdministratorDTO::from).collect(Collectors.toList());
    }

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

    // </editor-fold>

    // <editor-fold desc="Methods">

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // </editor-fold>
}
