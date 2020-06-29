package Expressions;

import Expressions.Values.Value;

public class TimesExpr extends BinopExpr {
    public TimesExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected Value evalHelp(Value left, Value right) {
        return left.mulWith(right);
    }
}
