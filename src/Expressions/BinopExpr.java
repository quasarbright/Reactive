package Expressions;

import Expressions.Values.Value;

import java.util.function.Function;

public abstract class BinopExpr implements Expr {
    protected final Expr left, right;

    public BinopExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }
    protected abstract Value evalHelp(Value left, Value right);

    @Override
    public Value evaluate(Function<String, Value> env) {
        Value left = this.left.evaluate(env);
        Value right = this.right.evaluate(env);
        return this.evalHelp(left, right);
    }
}
