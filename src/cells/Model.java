package cells;

import expressions.Expr;
import expressions.values.Value;

import java.util.Map;

public interface Model<Name> {
    /**
     * Get the specified cell
     * @param name the name of the cell
     * @return the cell
     * @throws IllegalArgumentException if name not found
     */
    Cell<Name> getCell(Name name);


    /**
     * Get the expr of the specified cell
     * @param name the name of the cell
     * @return the expr of the cell
     * @throws IllegalArgumentException if name not found
     */
    Expr<Name> getExpr(Name name);

    /**
     * Get the fully evaluated value of the specified cell.
     * @param name the name of the cell
     * @return the fully evaluated value of the specified cell
     * @throws IllegalArgumentException if name not found
     */
    Value<Name> getValue(Name name);

    /**
     * Get all cell values.
     * @return all cell values
     */
    Map<Name, Value<Name>> getValues();

    /**
     * Sets the contents of the specified cell, or creates it if it doesn't exist.
     * Responsible for updating cell values appropriately
     * @param name the name of the specified cell
     * @param expr the contents of the cell
     */
    void setCell(Name name, Expr<Name> expr);
}
