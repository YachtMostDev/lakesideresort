package nl.yacht.lakesideresort.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Already exists")
//400
public class AlreadyExistException extends RuntimeException{
}
