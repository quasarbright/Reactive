package diffName;

import java.util.function.Consumer;

/**
 * Either a variable, or change in a variable
 */
public interface DiffName {
    boolean isDelta();
    boolean isVar();

    void ifDelta(Consumer<DiffName> action);
    void ifVar(Consumer<String> action);

    /**
     * If this is a delta, return what this name is a change of.
     * @return what this name is a change of
     * @throws IllegalStateException if this is not a delta
     */
    DiffName getDelta();

    /**
     * If this is a var, return this var's name.
     * @return this var's name
     * @throws IllegalStateException if this is not a var
     */
    String getVar();
}
