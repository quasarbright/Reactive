package diffName;

import java.util.function.Consumer;

/**
 * Either a variable, or change in a variable
 */
public interface DiffName extends Comparable<DiffName> {
    boolean isDelta();
    boolean isVar();

    int depth();

    void ifDelta(Consumer<DiffName> action);
    void ifVar(Consumer<String> action);

    /**
     * If this is a delta, return what this name is a change of.
     * @return what this name is a change of
     * @throws IllegalStateException if this is not a delta
     */
    DiffName getDelta();

    /**
     * Get the var this DiffName describes (for dx, it'd be x)
     * @return this DiffName's var
     */
    String getVar();

    /**
     *
     * @return a pretty representation of this name.
     */
    String pretty();

    @Override
    default int compareTo(DiffName diffName) {
        int strComp = this.getVar().compareTo(diffName.getVar());
        if(strComp == 0) {
            return this.depth() - diffName.depth();
        } else {
            return strComp;
        }
    }
}
