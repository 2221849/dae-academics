package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

    // <editor-fold desc="Fields">

    private String username;

    private String name;

    private String email;

    private String role;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    @SuppressWarnings("unused")
    public UserDTO() {
    }

    public UserDTO(String username, String name, String email, String role) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // </editor-fold>

    // <editor-fold desc="Methods">

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                Hibernate.getClass(user).getSimpleName()
        );
    }
    public static List<UserDTO> from(List<User> users) {
        return users.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    // </editor-fold>
}