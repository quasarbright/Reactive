package expressions;

import expressions.values.Value;

public class MinusExpr<Name> extends BinopExpr<Name> {
    public MinusExpr(Expr<Name> left, Expr<Name> right) {
        super(left, right);
    }

    @Override
    protected Value<Name> evalHelp(Value<Name> left, Value<Name> right) {
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
