package com.koodu.exception;

import com.koodu.models.Response;
import com.koodu.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Abiola.Adebanjo
 */
@ControllerAdvice
public class BookmarkExceptionHandler {

    @ExceptionHandler(BookmarkException.class)
    public ResponseEntity<Response> constraintViolation(BookmarkException be) {
        return new ResponseEntity<>(new Response(be.getError()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> constraintViolation(Exception te) {
        te.printStackTrace();
        return new ResponseEntity<>(new Response(new Error("01", "Server Error")), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
