package stdbtt.quoter.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stdbtt.quoter.dto.QuoteDTO;
import stdbtt.quoter.service.QuoteService;
import stdbtt.quoter.util.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    private final DTOConverter dtoConverter;
    private final EntityConverter entityConverter;

    @Autowired
    public QuoteController(QuoteService quoteService, DTOConverter dtoConverter, EntityConverter entityConverter) {
        this.quoteService = quoteService;
        this.dtoConverter = dtoConverter;
        this.entityConverter = entityConverter;
    }

    @GetMapping("/{id}")
    public QuoteDTO getQuote(@PathVariable("id") Long id){
        return entityConverter.convert(quoteService.getQuote(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public Long addQuote(@RequestBody @Valid QuoteDTO quoteDTO, BindingResult bindingResult){
        checkErrors(bindingResult);
        return quoteService.addQuote(dtoConverter.convert(quoteDTO));
    }
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public void updateQuote(@PathVariable("id") Long id, @RequestBody @Valid QuoteDTO quoteDTO, BindingResult bindingResult){
        checkErrors(bindingResult);
        quoteDTO.setId(id);
        quoteService.updateQuote(dtoConverter.convert(quoteDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable("id") Long id){
        quoteService.deleteQuote(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/downvote")
    public void downvoteQuote(@PathVariable("id") Long quoteId){
        quoteService.voteForQuote(quoteId, Vote.DOWNVOTE);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/upvote")
    public void upvoteQuote(@PathVariable("id") Long quoteId){
        quoteService.voteForQuote(quoteId, Vote.UPVOTE);
    }

    @GetMapping("/random")
    public QuoteDTO getRandomQuote(){
        return entityConverter.convert(quoteService.getRandomQuote());
    }

    @GetMapping("/top10")
    public List<QuoteDTO> getTop10Quotes(){
        return quoteService.getTop10Quotes().stream().map(entityConverter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/worst10")
    public List<QuoteDTO> getWorst10Quotes(){
        return quoteService.getWorst10Quotes().stream().map(entityConverter::convert)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFieldsValuesException.class)
    public ErrorResponse handleFailValidationFields(InvalidFieldsValuesException e){
        return new ErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleFailedEntityLookup(EntityNotFoundException e){
        return new ErrorResponse(e.getMessage());
    }

    private void checkErrors(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidFieldsValuesException(MessageBuilder.buildMessage(bindingResult.getFieldErrors()));
        }
    }


}
