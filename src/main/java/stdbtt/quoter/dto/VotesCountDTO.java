package stdbtt.quoter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VotesCountDTO {
    @JsonProperty("upvotes")
    private int upvotesCount;

    @JsonProperty("downvotes")
    private int downvotesCount;

    public VotesCountDTO() {
    }

    public VotesCountDTO(int upvotesCount, int downvotesCount) {
        this.upvotesCount = upvotesCount;
        this.downvotesCount = downvotesCount;
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
}
