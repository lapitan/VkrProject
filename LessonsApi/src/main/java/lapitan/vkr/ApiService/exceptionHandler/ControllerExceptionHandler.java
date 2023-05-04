package lapitan.vkr.ApiService.exceptionHandler;

import lapitan.vkr.ApiService.user.exception.NoSuchUserException;
import lapitan.vkr.ApiService.user.exception.NotUniqueUsernameException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotUniqueUsernameException.class)
    public ResponseEntity<BaseWebResponse> handleNotUniqueUsernameException(@NonNull final NotUniqueUsernameException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<BaseWebResponse> handleNoSuchUserException(@NonNull final NoSuchUserException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseWebResponse(createErrorMessage(exc)));
    }

    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error(ExceptionHandlerUtils.buildErrorMessage(exception));
        return message;
    }
}
