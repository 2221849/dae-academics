package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "email"
                )
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Extra: try the other strategies… what happens to the database?
public class User {

    // <editor-fold desc="Fields">

    @Id
    @NotBlank(message = "Username cannot be blank")
    protected String username;

    @NotBlank(message = "Password cannot be blank")
    protected String password;

    @NotBlank(message = "Name cannot be blank")
    protected String name;

    @Email
    protected String email;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    public User() {
    }

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    // </editor-fold>
}