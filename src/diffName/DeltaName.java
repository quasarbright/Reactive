package diffName;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents a change in another variable
 */
public class DeltaName implements DiffName {
    public final DiffName var;

    public DeltaName(DiffName var) {
        this.var = var;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeltaName deltaName = (DeltaName) o;
        return var.equals(deltaName.var);
    }

    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    @Override
    public String toString() {
        return this.pretty();
    }

    @Override
    public boolean isDelta() {
        return true;
    }

    @Override
    public boolean isVar() {
        return false;
    }

    @Override
    public int depth() {
        return 1+this.var.depth();
    }

    @Override
    public void ifDelta(Consumer<DiffName> action) {
        action.accept(this.var);
    }

    @Override
    public void ifVar(Consumer<String> action) {

    }

    @Override
    public DiffName getDelta() {
        return this.var;
    }

    @Override
    public String getVar() {
        return this.var.getVar();
    }

    @Override
    public String pretty() {
        return new StringBuilder()
                .append("d")
                .append(this.var.pretty())
                .toString();
    }


}
