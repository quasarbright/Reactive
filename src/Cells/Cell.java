package Cells;

import Expressions.Expr;
import Expressions.Values.Value;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return name.equals(cell.name) &&
                expr.equals(cell.expr) &&
                value.equals(cell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expr, value);
    }
}
