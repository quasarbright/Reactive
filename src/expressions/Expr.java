package expressions;

import expressions.values.Value;

import java.util.Set;
import java.util.function.Function;

/**
 * Expression
 * @param <Name> hashable name type
 */
public interface Expr<Name> {
    /**
     * Evaluate this expression
     * @param env values of variables by name
     * @return the value of this expression
     */
    Value<Name> evaluate(Function<Name, Value<Name>> env);

    /**
     * @return variables occurring free in this expression
     */
    Set<Name> getFreeVars();

    /**
     *
     * @return a pretty representation of this name.
     */
    String pretty();
}
