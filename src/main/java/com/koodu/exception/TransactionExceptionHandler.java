package com.koodu.exception;

import com.koodu.models.Response;
import com.koodu.utils.Constants;
import com.koodu.utils.Utils;
import com.mongodb.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Abiola.Adebanjo
 */
@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<Response> constraintViolation(TransactionException be) {
        return new ResponseEntity<>(be.getResponse(), Utils.getHttpStatusFromResponseCode(be.getResponse().getResponseCode()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Response> constraintViolation(DuplicateKeyException dpke) {
        Response response = new Response(Constants.DUPLICATE_ERROR_CODE, Constants.DUPLICATE_ERROR_MESSAGE);
        return new ResponseEntity<>(response, Utils.getHttpStatusFromResponseCode(response.getResponseCode()));
    }

    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public ResponseEntity<Response> constraintViolation(org.springframework.dao.DuplicateKeyException dpke) {
        dpke.printStackTrace();
        Response response = new Response(Constants.DUPLICATE_ERROR_CODE, Constants.DUPLICATE_ERROR_MESSAGE);
        return new ResponseEntity<>(response, Utils.getHttpStatusFromResponseCode(response.getResponseCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> constraintViolation(MethodArgumentNotValidException manve) {
        Response response = new Response(Constants.INVALID_TRANSACTION_ERROR_CODE, manve.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, Utils.getHttpStatusFromResponseCode(response.getResponseCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> constraintViolation(Exception te) {
        te.printStackTrace();
        return new ResponseEntity<>(new Response(Constants.SERVER_ERROR_CODE, Constants.SERVER_ERROR_MESSAGE), Utils.getHttpStatusFromResponseCode(Constants.SERVER_ERROR_CODE));
    }
}
