package Expressions.Values;

public class DoubleValue implements Value {
    private double val;

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
        return new DoubleValue(this.val - i);
    }

    @Override
    public Value subDouble(double d) {
        return new DoubleValue(this.val - d);
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
        if (i == 0) {
            return new ErrorValue("divide by zero");
        } else {
            return new DoubleValue(this.val / i);
        }
    }

    @Override
    public Value divDouble(double d) {
        if (d == 0) {
            return new ErrorValue("divide by zero");
        } else {
            return new DoubleValue(this.val / d);
        }
    }
}
