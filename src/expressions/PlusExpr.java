package expressions;

import expressions.values.Value;

public class PlusExpr<Name> extends BinopExpr<Name> {
    public PlusExpr(Expr<Name> left, Expr<Name> right) {
        super(left, right);
    }

    @Override
    protected Value<Name> evalHelp(Value<Name> left, Value<Name> right) {
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
