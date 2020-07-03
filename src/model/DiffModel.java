package model;

import diffName.DiffName;
import expressions.Expr;
import expressions.PlusExpr;
import expressions.values.Value;

import java.util.Map;

/**
 * A model which can track differentials and deltas of variables as well as variables.
 * Ex: dx = v * dt, dv = a * dt, a = F / m, F = -k * x, m = 1, k = 1, x = 1, v = 0, dt = 0.01, t = 0
 */
public class DiffModel implements Model<DiffName> {
    private final Model<DiffName> delegate;

    public DiffModel(Model<DiffName> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Cell<DiffName> getCell(DiffName diffName) {
        return delegate.getCell(diffName);
    }

    @Override
    public Expr<DiffName> getExpr(DiffName diffName) {
        return delegate.getExpr(diffName);
    }

    @Override
    public Value<DiffName> getValue(DiffName diffName) {
        return delegate.getValue(diffName);
    }

    @Override
    public Map<DiffName, Value<DiffName>> getValues() {
        return delegate.getValues();
    }

    @Override
    public void setCell(DiffName diffName, Expr<DiffName> expr) {
        delegate.setCell(diffName, expr);
    }

    /**
     * Updates values according to their deltas.
     */
    public void update() {
        // copy of the previous state
        // we want simultaneous updates
        Map<DiffName, Value<DiffName>> values = this.getValues();
        values.keySet().forEach(
                delta -> delta.ifDelta((var -> {
                    if(!values.containsKey(var)) {
                        throw new IllegalStateException("tried to update "+var+" by its delta, but it was not found. Maybe you forgot to specify its initial value?");
                    } else {
                        Value<DiffName> current = values.get(var);
                        Value<DiffName> change = values.get(delta);
                        this.setCell(var, new PlusExpr<>(current, change));
                    }
                })));
    }
}
