package Expressions.Values;

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
}
