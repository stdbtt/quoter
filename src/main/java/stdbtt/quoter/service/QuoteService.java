package stdbtt.quoter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.quoter.dao.QuoteRepository;
import stdbtt.quoter.dao.UserRepository;
import stdbtt.quoter.dao.VotesCountRepository;
import stdbtt.quoter.model.Quote;
import stdbtt.quoter.model.User;
import stdbtt.quoter.model.VotesCount;
import stdbtt.quoter.util.EntityNotFoundException;
import stdbtt.quoter.util.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final VotesCountRepository votesCountRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository, VotesCountRepository votesCountRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.votesCountRepository = votesCountRepository;
    }

    @Transactional(readOnly = true)
    public Quote getQuote(Long id){
        Optional<Quote> quoteOptional = quoteRepository.findById(id);
        if(quoteOptional.isEmpty()){
            throw new EntityNotFoundException("Quote with id ["+id+"] is doesn't exist.");
        }
        return quoteOptional.get();
    }

    @Transactional
    public Long addQuote(Quote quote){
        String userName = quote.getUser().getName();
        User filledUser = userRepository.getUserByName(userName);
        if(filledUser==null){
            throw new EntityNotFoundException("User with name ["+userName+"] is doesn't exist.");
        }
        quote.setUser(filledUser);
        quote.setDateOfUpdate(LocalDateTime.now());
        Long quoteId = quoteRepository.save(quote).getId();
        VotesCount votesCount = new VotesCount();
        votesCount.setQuote(quote);
        votesCountRepository.save(votesCount);
        return quoteId;
    }

    @Transactional
    public void updateQuote(Quote updatedQuote){
        Long id = updatedQuote.getId();
        Optional<Quote> quoteOptional = quoteRepository.findById(id);
        if(quoteOptional.isEmpty()){
            throw new EntityNotFoundException("Quote with id ["+id+"] is doesn't exist.");
        }
        Quote quoteFromDB = quoteOptional.get();
        quoteFromDB.setContent(updatedQuote.getContent());
        quoteFromDB.setDateOfUpdate(LocalDateTime.now());
        quoteRepository.save(quoteFromDB);
    }

    @Transactional
    public void deleteQuote(Long id){
        if(!quoteRepository.existsById(id)){
            throw new EntityNotFoundException("Quote with id ["+id+"] is doesn't exist.");
        }
        quoteRepository.deleteById(id);
    }

    @Transactional
    public void voteForQuote(Long quoteId, Vote vote){
        VotesCount votesCount = votesCountRepository.findByQuoteId(quoteId);
        if(votesCount==null){
            throw new EntityNotFoundException("Quote with id ["+quoteId+"] is doesn't exist.");
        }
        int oldVotesCount;
        switch(vote){
            case UPVOTE -> {
                oldVotesCount=votesCount.getUpvotesCount();
                votesCount.setUpvotesCount(++oldVotesCount);
            }
            case DOWNVOTE -> {
                oldVotesCount=votesCount.getDownvotesCount();
                votesCount.setDownvotesCount(++oldVotesCount);
            }
        }
        votesCountRepository.save(votesCount);
    }

    @Transactional(readOnly = true)
    public Quote getRandomQuote() {
        return quoteRepository.getRandomQuote();
    }

    @Transactional(readOnly = true)
    public List<Quote> getTop10Quotes() {
        return quoteRepository.getTop10Quotes();
    }

    @Transactional(readOnly = true)
    public List<Quote> getWorst10Quotes() {
        return quoteRepository.getWorst10Quotes();
    }
}
