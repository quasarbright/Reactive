package view;

import diffName.DiffName;
import model.DiffModel;
import model.Model;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableView implements VisualView {
    private final JFrame frame;
    private final List<ViewListener> listeners;

    public TableView(DiffModel model) {
        this.frame = new JFrame("Reactive");
        this.listeners = new ArrayList<>();
        this.frame.setSize(400, 400);
        this.frame.setResizable(true);
        AbstractTableModel tableModel = new ModelTableModel(model);
        ViewForm viewForm = new ViewForm(tableModel, this.listeners);
        this.frame.setContentPane(viewForm.panelMain);
    }

    @Override
    public void show() {
        this.update();
        this.frame.setVisible(true);
        this.update();
    }

    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    @Override
    public void addListener(ViewListener listener) {
        this.listeners.add(listener);
    }

    private void update() {
        this.frame.repaint();
        this.frame.revalidate();
    }
}
