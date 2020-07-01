package Expressions;

import Expressions.Values.Value;

import java.util.Objects;
import java.util.function.Function;

public class VarExpr implements Expr {
    private final String name;

    public VarExpr(String name) {
        this.name = name;
    }

    @Override
    public Value evaluate(Function<String, Value> env) {
        return env.apply(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarExpr varExpr = (VarExpr) o;
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
}
