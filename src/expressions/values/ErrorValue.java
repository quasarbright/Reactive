package expressions.values;

import java.util.Objects;

public class ErrorValue<Name> implements Value<Name> {
    private final String message;

    public ErrorValue(String message) {
        this.message = message;
    }

    @Override
    public Value<Name> addWith(Value<Name> that) {
        return this;
    }

    @Override
    public Value<Name> addInt(int i) {
        return this;
    }

    @Override
    public Value<Name> addDouble(double d) {
        return this;
    }

    @Override
    public Value<Name> subWith(Value<Name> that) {
        return this;
    }

    @Override
    public Value<Name> subInt(int i) {
        return this;
    }

    @Override
    public Value<Name> subDouble(double d) {
        return this;
    }

    @Override
    public Value<Name> mulWith(Value<Name> that) {
        return this;
    }

    @Override
    public Value<Name> mulInt(int i) {
        return this;
    }

    @Override
    public Value<Name> mulDouble(double d) {
        return this;
    }

    @Override
    public Value<Name> divWith(Value<Name> that) {
        return this;
    }

    @Override
    public Value<Name> divInt(int i) {
        return this;
    }

    @Override
    public Value<Name> divDouble(double d) {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorValue<?> that = (ErrorValue<?>) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ErrorValue<Name>{" +
                "message='" + message + '\'' +
                '}';
    }
}
