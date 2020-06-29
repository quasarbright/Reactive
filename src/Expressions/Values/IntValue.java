package Expressions.Values;

public class IntValue implements Value {
    private int val;

    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public Value addWith(Value that) {
        return that.addInt(this.val);
    }

    @Override
    public Value addInt(int i) {
        return new IntValue(this.val + i);
    }

    @Override
    public Value addDouble(double d) {
        return new DoubleValue(this.val + d);
    }

    @Override
    public Value subWith(Value that) {
        return that.subInt(this.val);
    }

    @Override
    public Value subInt(int i) {
        return new IntValue(this.val - i);
    }

    @Override
    public Value subDouble(double d) {
        return new DoubleValue(this.val - d);
    }

    @Override
    public Value mulWith(Value that) {
        return that.mulInt(this.val);
    }

    @Override
    public Value mulInt(int i) {
        return new IntValue(this.val * i);
    }

    @Override
    public Value mulDouble(double d) {
        return new DoubleValue(this.val * d);
    }

    @Override
    public Value divWith(Value that) {
        return that.divInt(this.val);
    }

    @Override
    public Value divInt(int i) {
        if(i == 0) {
            return new ErrorValue("divide by zero");
        } else {
            return new IntValue(this.val / i);
        }
    }

    @Override
    public Value divDouble(double d) {
        if(d == 0) {
            return new ErrorValue("divide by zero");
        } else {
            return new DoubleValue(this.val / d);
        }
    }
}
