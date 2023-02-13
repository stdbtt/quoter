package stdbtt.quoter.util;

import org.springframework.validation.FieldError;

import java.util.List;

public class MessageBuilder {
    public static String buildMessage(List<FieldError> fieldErrors){
        StringBuilder errors = new StringBuilder();
        for(FieldError error : fieldErrors){
            errors.append("[")
                    .append(error.getField())
                    .append("] - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return errors.toString();
    }
}
