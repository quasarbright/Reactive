package diffName;

import java.util.Objects;
import java.util.function.Consumer;

public class VarName implements DiffName {
    public final String name;

    public VarName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarName varName = (VarName) o;
        return name.equals(varName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.pretty();
    }

    @Override
    public boolean isDelta() {
        return false;
    }

    @Override
    public boolean isVar() {
        return true;
    }

    @Override
    public int depth() {
        return 0;
    }

    @Override
    public void ifDelta(Consumer<DiffName> action) {

    }

    @Override
    public void ifVar(Consumer<String> action) {
        action.accept(this.name);
    }

    @Override
    public DiffName getDelta() {
        throw new IllegalStateException("not a delta");
    }

    @Override
    public String getVar() {
        return this.name;
    }

    @Override
    public String pretty() {
        return this.name;
    }
}
