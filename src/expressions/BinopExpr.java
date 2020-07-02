package expressions;

import expressions.values.Value;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public abstract class BinopExpr<Name> implements Expr<Name> {
    protected final Expr<Name> left, right;

    public BinopExpr(Expr<Name> left, Expr<Name> right) {
        this.left = left;
        this.right = right;
    }
    protected abstract Value<Name> evalHelp(Value<Name> left, Value<Name> right);

    @Override
    public Value<Name> evaluate(Function<Name, Value<Name>> env) {
        Value<Name> left = this.left.evaluate(env);
        Value<Name> right = this.right.evaluate(env);
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
        BinopExpr<?> binopExpr = (BinopExpr<?>) o;
        return left.equals(binopExpr.left) &&
                right.equals(binopExpr.right);
    }

    @Override
    public Set<Name> getFreeVars() {
        Set<Name> left = new HashSet<>(this.left.getFreeVars());
        Set<Name> right = this.right.getFreeVars();
        left.addAll(right);
        return left;
    }
}
