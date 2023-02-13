package stdbtt.quoter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserCreationDTO {

    @Size(max = 32, message = "Name should not be longer than 32 symbols.")
    private String name;

    @Size(max = 64, message = "Email should not be longer than 64 symbols.")
    @Email(message = "Incorrect format of email.")
    private String email;

    @Size(min=8, max=16, message = "Password should have length between 8 and 16.")
    private String password;

    public UserCreationDTO() {
    }

    public UserCreationDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
