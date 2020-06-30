package Expressions;

import Expressions.Values.Value;

import java.util.function.Function;

public class DivideExpr extends BinopExpr {
    public DivideExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected Value evalHelp(Value left, Value right) {
        return left.divWith(right);
    }

    @Override
    public String toString() {
        return "DivideExpr{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
