package expressions;

import expressions.values.Value;

public class MinusExpr extends BinopExpr {
    public MinusExpr(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    protected Value evalHelp(Value left, Value right) {
        return left.subWith(right);
    }

    @Override
    public String toString() {
        return "MinusExpr{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}