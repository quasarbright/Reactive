package Expressions.Values;

public class ErrorValue implements Value {
    private String message;

    public ErrorValue(String message) {
        this.message = message;
    }
}
