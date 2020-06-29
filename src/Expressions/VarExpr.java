package Expressions;

import Expressions.Values.Value;

import java.util.function.Function;

public class VarExpr implements Expr {
    private String name;

    public VarExpr(String name) {
        this.name = name;
    }

    @Override
    public Value evaluate(Function<String, Value> env) {
        return env.apply(this.name);
    }
}
