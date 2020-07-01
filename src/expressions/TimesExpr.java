package expressions;

import expressions.values.Value;

public class TimesExpr extends BinopExpr {
    public TimesExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected Value evalHelp(Value left, Value right) {
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
