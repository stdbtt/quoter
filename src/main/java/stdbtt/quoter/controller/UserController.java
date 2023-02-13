package stdbtt.quoter.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stdbtt.quoter.dto.UserCreationDTO;
import stdbtt.quoter.service.UserService;
import stdbtt.quoter.util.DTOConverter;
import stdbtt.quoter.util.ErrorResponse;
import stdbtt.quoter.util.InvalidFieldsValuesException;
import stdbtt.quoter.util.MessageBuilder;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final DTOConverter dtoConverter;

    @Autowired
    public UserController(UserService userService, DTOConverter dtoConverter) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public void createUser(@RequestBody @Valid UserCreationDTO userCreationDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidFieldsValuesException(MessageBuilder.buildMessage(bindingResult.getFieldErrors()));
        }
        userService.createUser(dtoConverter.convert(userCreationDTO));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFieldsValuesException.class)
    public ErrorResponse handleFailValidationUser(InvalidFieldsValuesException e){
       return new ErrorResponse(e.getMessage());
    }
}
