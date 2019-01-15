package windpark.model;

public class WindengineMessage {

    private String message;

    public WindengineMessage() {
    }

    public WindengineMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Mmail{message=%s}", getMessage());
    }

}