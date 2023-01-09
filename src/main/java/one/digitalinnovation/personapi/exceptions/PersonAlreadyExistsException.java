package one.digitalinnovation.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PersonAlreadyExistsException extends RuntimeException {

    public PersonAlreadyExistsException(String cpf) {
        super(String.format("Person already exists with CPF %s.", cpf));
    }
}
