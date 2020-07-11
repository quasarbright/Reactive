package expressions;

import expressions.values.IntValue;
import expressions.values.Value;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class NegExpr<Name> implements Expr<Name> {
    private final Expr<Name> child;

    public NegExpr(Expr<Name> child) {
        this.child = child;
    }

    @Override
    public Value<Name> evaluate(Function<Name, Value<Name>> env) {
        Expr<Name> equivalent = new TimesExpr<>(new IntValue<>(-1), this.child);
        return equivalent.evaluate(env);
    }

    @Override
    public Set<Name> getFreeVars() {
        return this.child.getFreeVars();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NegExpr<?> negExpr = (NegExpr<?>) o;
        return child.equals(negExpr.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(child);
    }

    @Override
    public String toString() {
        return "NegExpr{" +
                "child=" + child +
                '}';
    }

    @Override
    public String pretty() {
        return new StringBuilder()
                .append("-(")
                .append(this.child.pretty())
                .append(")")
                .toString();
    }
}
