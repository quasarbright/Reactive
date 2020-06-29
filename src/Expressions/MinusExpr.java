package Expressions;

import Expressions.Values.Value;

public class MinusExpr extends BinopExpr {
    public MinusExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected Value evalHelp(Value left, Value right) {
        return left.mulWith(right);
    }
}
