package training.spring.microservice.mscommon.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    @Autowired
    private ErrorConfiguration errorConfiguration;


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorCode(1048)
                       .withErrorDesc(exp.getMessage())
                       .withBoundedContext(errorConfiguration.getBoundedContext())
                       .withMicroservice(errorConfiguration.getMicroservice())
                       .build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handleException(IllegalStateException exp) {
        return ErrorObj.builder()
                       .withErrorCode(1076)
                       .withErrorDesc(exp.getMessage())
                       .withBoundedContext(errorConfiguration.getBoundedContext())
                       .withMicroservice(errorConfiguration.getMicroservice())
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withErrorCode(1050)
                       .withErrorDesc("validation problem")
                       .withSubErrors(exp.getAllErrors()
                                         .stream()
                                         .map(e -> ErrorObj.builder()
                                                           .withErrorDesc(e.toString())
                                                           .withErrorCode(1049)
                                                           .build())
                                         .collect(Collectors.toList()))
                       .withBoundedContext(errorConfiguration.getBoundedContext())
                       .withMicroservice(errorConfiguration.getMicroservice())
                       .build();
    }

    @ExceptionHandler(RemoteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObj handleException(RemoteException exp) {
        logger.error("[ErrorAdvice][handleException]-> *Error* Remote Call : " + exp.getMessage());
        return ErrorObj.builder()
                       .withErrorCode(1098)
                       .withErrorDesc("Error while calling microservice")
                       .withSubErrors(Arrays.asList(exp.getErrorObj()))
                       .withBoundedContext(errorConfiguration.getBoundedContext())
                       .withMicroservice(errorConfiguration.getMicroservice())
                       .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleException(Exception exp) {
        logger.error("[ErrorAdvice][handleException]-> *Error* : " + exp.getMessage(),
                     exp);
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(ErrorObj.builder()
                                               .withErrorCode(1048)
                                               .withErrorDesc(exp.getMessage())
                                               .withBoundedContext(errorConfiguration.getBoundedContext())
                                               .withMicroservice(errorConfiguration.getMicroservice())
                                               .build());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorCode(1048)
                                           .withErrorDesc(exp.getMessage())
                                           .withBoundedContext(errorConfiguration.getBoundedContext())
                                           .withMicroservice(errorConfiguration.getMicroservice())
                                           .build());
    }

}
