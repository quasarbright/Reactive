package Expressions;

import Expressions.Values.Value;

public abstract class BinopExpr implements Expr {
    protected final Expr left, right;

    public BinopExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    protected abstract Value evalHelp(Value left, Value right);
}
