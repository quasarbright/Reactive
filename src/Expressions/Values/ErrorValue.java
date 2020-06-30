package Expressions.Values;

import java.util.Objects;

public class ErrorValue implements Value {
    private String message;

    public ErrorValue(String message) {
        this.message = message;
    }

    @Override
    public Value addWith(Value that) {
        return this;
    }

    @Override
    public Value addInt(int i) {
        return this;
    }

    @Override
    public Value addDouble(double d) {
        return this;
    }

    @Override
    public Value subWith(Value that) {
        return this;
    }

    @Override
    public Value subInt(int i) {
        return this;
    }

    @Override
    public Value subDouble(double d) {
        return this;
    }

    @Override
    public Value mulWith(Value that) {
        return this;
    }

    @Override
    public Value mulInt(int i) {
        return this;
    }

    @Override
    public Value mulDouble(double d) {
        return this;
    }

    @Override
    public Value divWith(Value that) {
        return this;
    }

    @Override
    public Value divInt(int i) {
        return this;
    }

    @Override
    public Value divDouble(double d) {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorValue that = (ErrorValue) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ErrorValue{" +
                "message='" + message + '\'' +
                '}';
    }
}
