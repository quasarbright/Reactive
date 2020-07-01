package Expressions.Values;

import java.util.Objects;

public class DoubleValue implements Value {
    private final double val;

    public DoubleValue(double val) {
        this.val = val;
    }

    @Override
    public Value addWith(Value that) {
        return that.addDouble(this.val);
    }

    @Override
    public Value addInt(int i) {
        return new DoubleValue(this.val + i);
    }

    @Override
    public Value addDouble(double d) {
        return new DoubleValue(this.val + d);
    }

    @Override
    public Value subWith(Value that) {
        return that.subDouble(this.val);
    }

    @Override
    public Value subInt(int i) {
        return new DoubleValue(i - this.val);
    }

    @Override
    public Value subDouble(double d) {
        return new DoubleValue(d - this.val);
    }

    @Override
    public Value mulWith(Value that) {
        return that.mulDouble(this.val);
    }

    @Override
    public Value mulInt(int i) {
        return new DoubleValue(this.val * i);
    }

    @Override
    public Value mulDouble(double d) {
        return new DoubleValue(this.val * d);
    }

    @Override
    public Value divWith(Value that) {
        return that.divDouble(this.val);
    }

    @Override
    public Value divInt(int i) {
        if (this.val == 0) {
            return new ErrorValue("divide by zero");
        } else {
            return new DoubleValue(i / this.val);
        }
    }

    @Override
    public Value divDouble(double d) {
        if (this.val == 0) {
            return new ErrorValue("divide by zero");
        } else {
            return new DoubleValue(d / this.val);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleValue that = (DoubleValue) o;
        return Double.compare(that.val, val) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "DoubleValue{" +
                "val=" + val +
                '}';
    }
}
