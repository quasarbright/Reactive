package expressions;

import diffName.DiffName;
import expressions.values.Value;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class VarExpr<Name> implements Expr<Name> {
    private final Name name;

    public VarExpr(Name name) {
        this.name = name;
    }

    @Override
    public Value<Name> evaluate(Function<Name, Value<Name>> env) {
        return env.apply(this.name);
    }

    @Override
    public Set<Name> getFreeVars() {
        return new HashSet<>(Set.of(this.name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarExpr<?> varExpr = (VarExpr<?>) o;
        return name.equals(varExpr.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "VarExpr{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String pretty() {
        // JANK
        if(this.name instanceof DiffName) {
            DiffName name = (DiffName) this.name;
            return name.pretty();
        } else {
            return this.name.toString();
        }
    }
}
