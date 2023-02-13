package stdbtt.quoter.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stdbtt.quoter.dto.QuoteDTO;
import stdbtt.quoter.dto.UserCreationDTO;
import stdbtt.quoter.dto.UserDTO;
import stdbtt.quoter.dto.VotesCountDTO;
import stdbtt.quoter.model.Quote;
import stdbtt.quoter.model.User;
import stdbtt.quoter.model.VotesCount;

@Component
public class DTOConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convert(UserCreationDTO userCreationDTO){
        return modelMapper.map(userCreationDTO, User.class);
    }

    public User convert(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public Quote convert(QuoteDTO quoteDTO){
        return modelMapper.map(quoteDTO, Quote.class);
    }

    public VotesCount convert(VotesCountDTO votesCountDTO){
        return modelMapper.map(votesCountDTO, VotesCount.class);
    }
}
