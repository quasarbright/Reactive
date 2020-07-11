package view;

import java.io.Reader;
import java.util.Optional;

public interface ViewListener {
    /**
     * Edit existing cell or create new cell
     * @param assignment a string representing an assignment
     * @return maybe an error message (for cyclic definition)
     */
    Optional<String> onEdit(String assignment);

    /**
     * Update the model (for deltas)
     */
    void onUpdate();

    void onSave(Appendable out);

    void onOpen(Reader in);
}
