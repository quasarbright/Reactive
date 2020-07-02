package expressions.values;

import expressions.Expr;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public interface Value<Name> extends Expr<Name> {
    @Override
    default Value<Name> evaluate(Function<Name, Value<Name>> env) {
        return this;
    }

    @Override
    default Set<Name> getFreeVars() {
        return new HashSet<>();
    }

    Value<Name> addWith(Value<Name> that);
    Value<Name> addInt(int i);
    Value<Name> addDouble(double d);

    Value<Name> subWith(Value<Name> that);
    Value<Name> subInt(int i);
    Value<Name> subDouble(double d);

    Value<Name> mulWith(Value<Name> that);
    Value<Name> mulInt(int i);
    Value<Name> mulDouble(double d);

    Value<Name> divWith(Value<Name> that);
    Value<Name> divInt(int i);
    Value<Name> divDouble(double d);
}
