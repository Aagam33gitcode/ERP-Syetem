package com.example.ERP.System.HR.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class globalExceptionhandler {

@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> getCustomExcetption(ResourceNotFoundException ex){
    ApiError error=new ApiError(ex.getMessage(),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
}
    @ExceptionHandler(ResourceNotFoundException.ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleEmployeeAlreadyExists(ResourceNotFoundException.ResourceAlreadyExistsException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)  // this picks your validation messages, e.g. "Email is required!"
                .collect(Collectors.toList());

        ApiError error = new ApiError("Invalid Email 😞", HttpStatus.BAD_REQUEST.value(), errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

   /* @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> resouceNotFoundError(NoSuchElementException exception){
        return new ResponseEntity<>("employee not have exist", HttpStatus.NOT_FOUND);
    }


    for the some messages/error details
        @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resouceNotFoundError(ResourceNotFoundException exception){
ApiError apiError=ApiError.bulider().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleCustomException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.message,exception.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleCustomException1(IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleCustomException1(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
*/



