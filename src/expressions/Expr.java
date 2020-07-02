package expressions;

import expressions.values.Value;

import java.util.Set;
import java.util.function.Function;

public interface Expr {
    /**
     * Evaluate this expression
     * @param env values of variables by name
     * @return the value of this expression
     */
    Value evaluate(Function<String, Value> env);

    /**
     * @return variables occurring free in this expression
     */
    Set<String> getFreeVars();
}