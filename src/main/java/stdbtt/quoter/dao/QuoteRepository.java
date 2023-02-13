package stdbtt.quoter.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stdbtt.quoter.model.Quote;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    boolean existsById(Long id);

    @Query("SELECT q FROM Quote q " +
            "JOIN FETCH q.user JOIN FETCH q.votesCount ORDER BY random() LIMIT 1")
    Quote getRandomQuote();

    @Query("SELECT q FROM Quote q " +
            "JOIN FETCH q.user JOIN FETCH q.votesCount v " +
            "ORDER BY v.upvotesCount-v.downvotesCount DESC LIMIT 10")
    List<Quote> getTop10Quotes();

    @Query("SELECT q FROM Quote q " +
            "JOIN FETCH q.user JOIN FETCH q.votesCount v " +
            "ORDER BY v.upvotesCount-v.downvotesCount ASC LIMIT 10")
    List<Quote> getWorst10Quotes();

}
