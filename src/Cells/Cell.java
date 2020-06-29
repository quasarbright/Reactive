package Cells;

import Expressions.Expr;
import Expressions.Values.Value;

import java.util.function.Function;

public class Cell {
    private String name;
    private Expr expr;
    private Value value;

    public Cell(String name, Expr expr, Value value) {
        this.name = name;
        this.expr = expr;
        this.value = value;
    }

    public void reevaluate(Function<String, Value> env) {
    }

    public Value getValue() {
        return null;
    }
}
