package expressions;

import expressions.values.Value;

public class TimesExpr<Name> extends BinopExpr<Name> {
    public TimesExpr(Expr<Name> left, Expr<Name> right) {
        super(left, right, "*");
    }

    @Override
    protected Value<Name> evalHelp(Value<Name> left, Value<Name> right) {
        return left.mulWith(right);
    }

    @Override
    public String toString() {
        return "TimesExpr{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
