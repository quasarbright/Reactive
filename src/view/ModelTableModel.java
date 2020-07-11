package view;

import diffName.DiffName;
import model.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class ModelTableModel extends AbstractTableModel {
    private final Model<DiffName> model;
    private final String[] columns = {"Name", "Expression", "Value"};

    ModelTableModel(Model<DiffName> model) {
        this.model = model;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return this.columns[column];
    }

    @Override
    public int getRowCount() {
        return model.getNames().size();
    }

    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        List<DiffName> names = this.model.getNames().stream()
                .sorted()
                .collect(Collectors.toList());
        // TODO implement comparator on DiffNames so x is next to dx
        DiffName name = names.get(row);
        switch (col) {
            case 0:
                return name.pretty();
            case 1:
                return model.getExpr(name).pretty();
            case 2:
                return model.getValue(name).pretty();
            default:
                throw new IllegalStateException();
        }
    }
}
