package userservices.userservices.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import userservices.userservices.Response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(ResourcerNotFoundException.class)
public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourcerNotFoundException ex) {
    String message = ex.getMessage();
    ApiResponse response=  ApiResponse.builder().message(message).success(false).build();
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
}

}
