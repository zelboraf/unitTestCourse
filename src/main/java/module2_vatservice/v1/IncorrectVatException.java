package module2_vatservice.v1;

public class IncorrectVatException extends Throwable {
    private final String message;
    public IncorrectVatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
