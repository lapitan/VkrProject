package lapitan.vkr.ApiService.user.exception;

public class NotUniqueUsernameException extends RuntimeException{
    public NotUniqueUsernameException(String message) {
        super(message);
    }
}
