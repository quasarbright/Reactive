package expressions;

import expressions.values.Value;

import java.util.Objects;
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

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // the getClass check makes sure times != divide ever
        if (o == null || getClass() != o.getClass()) return false;
        BinopExpr binopExpr = (BinopExpr) o;
        return left.equals(binopExpr.left) &&
                right.equals(binopExpr.right);
    }
}
