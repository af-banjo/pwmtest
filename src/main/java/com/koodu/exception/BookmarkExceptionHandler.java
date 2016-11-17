package com.koodu.exception;

import com.koodu.models.Response;
import com.koodu.models.Error;
import com.koodu.utils.Constants;
import com.koodu.utils.Utils;
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
        return new ResponseEntity<>(new Response(be.getError()), Utils.getHttpStatusFromResponseCode(be.getError().getCode()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> constraintViolation(Exception te) {
        te.printStackTrace();
        return new ResponseEntity<>(new Response(new Error(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE)), Utils.getHttpStatusFromResponseCode(Constants.SERVER_ERROR_CODE));
    }
}
