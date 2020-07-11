package expressions;

import expressions.values.Value;

public class DivideExpr<Name> extends BinopExpr<Name> {
    public DivideExpr(Expr<Name> left, Expr<Name> right) {
        super(left, right, "/");
    }

    @Override
    protected Value<Name> evalHelp(Value<Name> left, Value<Name> right) {
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
