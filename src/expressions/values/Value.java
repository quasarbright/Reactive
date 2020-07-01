package expressions.values;

import expressions.Expr;

import java.util.function.Function;

public interface Value extends Expr {
    @Override
    default Value evaluate(Function<String, Value> env) {
        return this;
    }

    Value addWith(Value that);
    Value addInt(int i);
    Value addDouble(double d);

    Value subWith(Value that);
    Value subInt(int i);
    Value subDouble(double d);

    Value mulWith(Value that);
    Value mulInt(int i);
    Value mulDouble(double d);

    Value divWith(Value that);
    Value divInt(int i);
    Value divDouble(double d);
}
