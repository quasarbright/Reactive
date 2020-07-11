package view;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ViewForm {
    private JTable table1;
    private JTextField assignmentInput;
    private JButton submitButton;
    private JButton updateButton;
    public JPanel panelMain;

    public ViewForm(TableModel tableModel, List<ViewListener> listeners) {
        this.table1.setModel(tableModel);
        ActionListener submitListener = e -> {
            String assignment = assignmentInput.getText();
            listeners.forEach(listener -> {
                Optional<String> maybeError = listener.onEdit(assignment);
                maybeError.ifPresent((message) -> {
                    JOptionPane.showMessageDialog(panelMain, message, "Error", JOptionPane.ERROR_MESSAGE);
                });
            });
            panelMain.repaint();
            panelMain.revalidate();
            table1.updateUI();
            assignmentInput.setText("");
        };
        submitButton.addActionListener(submitListener);
        assignmentInput.addActionListener(submitListener);
        updateButton.addActionListener(actionEvent -> {
            listeners.forEach(ViewListener::onUpdate);
            panelMain.repaint();
            panelMain.revalidate();
            table1.updateUI();
        });
    }
}
