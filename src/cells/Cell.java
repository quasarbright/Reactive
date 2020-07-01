package cells;

import expressions.Expr;
import expressions.values.Value;

import java.util.Objects;
import java.util.function.Function;

public class Cell {
    private final String name;
    private final Expr expr;
    private Value value;

    public Cell(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    public Cell(String name, Expr expr, Function<String, Value> env) {
        this.name = name;
        this.expr = expr;
        this.reevaluate(env);
    }

    public void reevaluate(Function<String, Value> env) {
        this.value = this.expr.evaluate(env);
    }

    public Value getValue() {
        return this.value;
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

    @Override
    public String toString() {
        return "Cell{" +
                "name='" + name + '\'' +
                ", expr=" + expr +
                ", value=" + value +
                '}';
    }
}
