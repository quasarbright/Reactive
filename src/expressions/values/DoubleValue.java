package expressions.values;

import java.util.Objects;

public class DoubleValue<Name> implements Value<Name> {
    private final double val;

    public DoubleValue(double val) {
        this.val = val;
    }

    @Override
    public Value<Name> addWith(Value<Name> that) {
        return that.addDouble(this.val);
    }

    @Override
    public Value<Name> addInt(int i) {
        return new DoubleValue<>(this.val + i);
    }

    @Override
    public Value<Name> addDouble(double d) {
        return new DoubleValue<>(this.val + d);
    }

    @Override
    public Value<Name> subWith(Value<Name> that) {
        return that.subDouble(this.val);
    }

    @Override
    public Value<Name> subInt(int i) {
        return new DoubleValue<>(i - this.val);
    }

    @Override
    public Value<Name> subDouble(double d) {
        return new DoubleValue<>(d - this.val);
    }

    @Override
    public Value<Name> mulWith(Value<Name> that) {
        return that.mulDouble(this.val);
    }

    @Override
    public Value<Name> mulInt(int i) {
        return new DoubleValue<>(this.val * i);
    }

    @Override
    public Value<Name> mulDouble(double d) {
        return new DoubleValue<>(this.val * d);
    }

    @Override
    public Value<Name> divWith(Value<Name> that) {
        return that.divDouble(this.val);
    }

    @Override
    public Value<Name> divInt(int i) {
        if (this.val == 0) {
            return new ErrorValue<>("divide by zero");
        } else {
            return new DoubleValue<>(i / this.val);
        }
    }

    @Override
    public Value<Name> divDouble(double d) {
        if (this.val == 0) {
            return new ErrorValue<>("divide by zero");
        } else {
            return new DoubleValue<>(d / this.val);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleValue<?> that = (DoubleValue<?>) o;
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

    @Override
    public String pretty() {
        return Double.toString(this.val);
    }
}
