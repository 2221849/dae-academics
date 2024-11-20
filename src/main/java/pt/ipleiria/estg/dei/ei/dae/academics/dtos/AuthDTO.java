package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AuthDTO implements Serializable {
    // <editor-fold desc="Fields">

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    public AuthDTO() {
    }

    public AuthDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    // </editor-fold>
}