package Expressions;

import Expressions.Values.Value;

public class PlusExpr extends BinopExpr {
    public PlusExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected Value evalHelp(Value left, Value right) {
        return left.addWith(right);
    }

    @Override
    public String toString() {
        return "PlusExpr{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
