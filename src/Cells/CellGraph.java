package Cells;

import Expressions.Expr;
import Expressions.Values.Value;

import java.util.Map;

public interface CellGraph {
    /**
     * Get the specified cell
     * @param name the name of the cell
     * @return the cell
     */
    Cell getCell(String name);


    /**
     * Get the expr of the specified cell
     * @param name the name of the cell
     * @return the expr of the cell
     */
    Expr getExpr(String name);

    /**
     * Get the fully evaluated value of the specified cell
     * @param name the name of the cell
     * @return the fully evaluated value of the specified cell
     */
    Value getValue(String name);

    /**
     * Get all cell values.
     * @return all cell values
     */
    Map<String, Value> getValues();

    /**
     * Sets the contents of the specified cell, or creates it if it doesn't exist.
     * Responsible for updating cell values appropriately
     * @param name the name of the specified cell
     * @param expr the contents of the cell
     */
    void setCell(String name, Expr expr);
}
