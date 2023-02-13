package stdbtt.quoter.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import stdbtt.quoter.model.VotesCount;

@Repository
public interface VotesCountRepository extends CrudRepository<VotesCount, Long> {
    @Query("SELECT v FROM VotesCount v WHERE v.quote.id = :id")
    VotesCount findByQuoteId(@Param("id") Long quoteId);
}
