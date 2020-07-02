package expressions.values;

import java.util.Objects;

public class IntValue<Name> implements Value<Name> {
    private final int val;

    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public Value<Name> addWith(Value<Name> that) {
        return that.addInt(this.val);
    }

    @Override
    public Value<Name> addInt(int i) {
        return new IntValue<>(this.val + i);
    }

    @Override
    public Value<Name> addDouble(double d) {
        return new DoubleValue<>(this.val + d);
    }

    @Override
    public Value<Name> subWith(Value<Name> that) {
        return that.subInt(this.val);
    }

    @Override
    public Value<Name> subInt(int i) {
        return new IntValue<>(i - this.val);
    }

    @Override
    public Value<Name> subDouble(double d) {
        return new DoubleValue<>(d - this.val);
    }

    @Override
    public Value<Name> mulWith(Value<Name> that) {
        return that.mulInt(this.val);
    }

    @Override
    public Value<Name> mulInt(int i) {
        return new IntValue<>(this.val * i);
    }

    @Override
    public Value<Name> mulDouble(double d) {
        return new DoubleValue<>(this.val * d);
    }

    @Override
    public Value<Name> divWith(Value<Name> that) {
        return that.divInt(this.val);
    }

    @Override
    public Value<Name> divInt(int i) {
        if(this.val == 0) {
            return new ErrorValue<>("divide by zero");
        } else {
            return new IntValue<>(i / this.val);
        }
    }

    @Override
    public Value<Name> divDouble(double d) {
        if(this.val == 0) {
            return new ErrorValue<>("divide by zero");
        } else {
            return new DoubleValue<>(d / this.val);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntValue<?> intValue = (IntValue<?>) o;
        return val == intValue.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "IntValue<Name>{" +
                "val=" + val +
                '}';
    }
}
