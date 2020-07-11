package view;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class ViewForm {
    private JTable table1;
    private JTextField assignmentInput;
    private JButton submitButton;
    private JButton updateButton;
    public JPanel panelMain;
    private JButton startButton;
    private JButton stopButton;
    private JFormattedTextField rateInput;
    public final ActionListener onUpdate;

    public ViewForm(TableModel tableModel, List<ViewListener> listeners, ViewFormListener viewFormListener) {
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
        this.onUpdate = actionEvent -> {
            listeners.forEach(ViewListener::onUpdate);
            panelMain.repaint();
            panelMain.revalidate();
            table1.updateUI();
        };
        updateButton.addActionListener(this.onUpdate);

        // all this for a positive int text box
        rateInput.setFormatterFactory(new JFormattedTextField.AbstractFormatterFactory() {
            @Override
            public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField jFormattedTextField) {
                NumberFormat f = NumberFormat.getNumberInstance();
                NumberFormatter numberFormatter = new NumberFormatter(f) {
                    @Override
                    public Object stringToValue(String text) throws ParseException {
                        System.out.println("text: '"+text+"'");
                        if(text.length() == 0) {
                            return null;
                        }
                        return super.stringToValue(text);
                    }
                };
                numberFormatter.setValueClass(Integer.class);
                numberFormatter.setAllowsInvalid(false);
                numberFormatter.setMinimum(0);
                return numberFormatter;
                }
            }
        );

        rateInput.addActionListener(e -> {
            Object val = rateInput.getValue();
            if(!(val instanceof Integer)) {
                viewFormListener.setRate(0);
            } else {
                viewFormListener.setRate((Integer) rateInput.getValue());
            }
        });

        startButton.addActionListener(e -> {
            Object val = rateInput.getValue();
            if(!(val instanceof Integer)) {
                viewFormListener.startAuto(0);
            } else {
                viewFormListener.startAuto((Integer) rateInput.getValue());
            }
        });

        stopButton.addActionListener(e -> {
            viewFormListener.stopAuto();
        });
    }

    public static interface ViewFormListener {
        void setRate(int rate);
        void startAuto(int rate);
        void stopAuto();
    }
}
