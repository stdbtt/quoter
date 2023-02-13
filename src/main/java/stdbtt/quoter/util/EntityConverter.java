package stdbtt.quoter.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stdbtt.quoter.dto.QuoteDTO;
import stdbtt.quoter.dto.UserDTO;
import stdbtt.quoter.dto.VotesCountDTO;
import stdbtt.quoter.model.Quote;
import stdbtt.quoter.model.User;
import stdbtt.quoter.model.VotesCount;

@Component
public class EntityConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public EntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convert(User user){
        if(user!=null){
            return modelMapper.map(user, UserDTO.class);
        }
        return null;
    }

    public QuoteDTO convert(Quote quote){
        if(quote!=null){
            UserDTO userDTO = convert(quote.getUser());
            VotesCountDTO votesCountDTO = convert(quote.getVotesCount());
            QuoteDTO quoteDTO = modelMapper.map(quote, QuoteDTO.class);
            quoteDTO.setUserDTO(userDTO);
            quoteDTO.setVotesCountDTO(votesCountDTO);
            return quoteDTO;
        }
        return null;
    }

    public VotesCountDTO convert(VotesCount votesCount){
        if(votesCount!=null){
            return modelMapper.map(votesCount, VotesCountDTO.class);
        }
        return null;
    }
}
