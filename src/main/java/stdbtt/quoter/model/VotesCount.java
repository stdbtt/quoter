package stdbtt.quoter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VOTES_COUNT")
public class VotesCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UPVOTES_COUNT", nullable = false)
    private int upvotesCount;

    @Column(name = "DOWNVOTES_COUNT", nullable = false)
    private int downvotesCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUOTE_ID")
    private Quote quote;

    public VotesCount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUpvotesCount() {
        return upvotesCount;
    }

    public void setUpvotesCount(int upvotesCount) {
        this.upvotesCount = upvotesCount;
    }

    public int getDownvotesCount() {
        return downvotesCount;
    }

    public void setDownvotesCount(int downvotesCount) {
        this.downvotesCount = downvotesCount;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
}
