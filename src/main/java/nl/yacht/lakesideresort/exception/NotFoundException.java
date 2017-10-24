package nl.yacht.lakesideresort.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No found result")
//404
public class NotFoundException extends RuntimeException{
    /* instead of return null, use:
     * if (... == null) throw new NotFoundException();
     */
}
