package stdbtt.quoter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class QuoteDTO {

    private Long id;

    @Size(max=280, message = "Content should not be longer than 280 symbols.")
    private String content;

    @JsonProperty("user")
    private UserDTO userDTO;

    @JsonProperty("votes")
    private VotesCountDTO votesCountDTO;

    public QuoteDTO() {
    }

    public QuoteDTO(String content, UserDTO userDTO, VotesCountDTO votesCountDTO) {
        this.content = content;
        this.userDTO = userDTO;
        this.votesCountDTO = votesCountDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public VotesCountDTO getVotesCountDTO() {
        return votesCountDTO;
    }

    public void setVotesCountDTO(VotesCountDTO votesCountDTO) {
        this.votesCountDTO = votesCountDTO;
    }
}
