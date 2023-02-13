package stdbtt.quoter.dto;

import jakarta.validation.constraints.Size;

public class UserDTO {

    @Size(max = 32, message = "Name should not be longer than 32 symbols.")
    private String name;

    public UserDTO() {
    }

    public UserDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
