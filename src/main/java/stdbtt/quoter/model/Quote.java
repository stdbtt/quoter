package stdbtt.quoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "QUOTE")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=280)
    @Column(name="CONTENT", nullable = false)
    private String content;

    @Column(name = "DATE_OF_UPDATE")
    private LocalDateTime dateOfUpdate;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private User user;

    @OneToOne(mappedBy = "quote")
    private VotesCount votesCount;

    public Quote() {
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

    public LocalDateTime getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(LocalDateTime dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VotesCount getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(VotesCount votesCount) {
        this.votesCount = votesCount;
    }
}
