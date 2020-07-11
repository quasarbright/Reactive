package view;

import diffName.DiffName;
import model.DiffModel;
import model.Model;
import org.antlr.v4.gui.JFileChooserConfirmOverwrite;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableView implements VisualView {
    private final JFrame frame;
    private final List<ViewListener> listeners;

    public TableView(DiffModel model) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ignored) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored1) { }
        }
        this.frame = new JFrame("Reactive");
        this.listeners = new ArrayList<>();
        this.frame.setSize(400, 400);
        this.frame.setResizable(true);
        AbstractTableModel tableModel = new ModelTableModel(model);
        ViewForm viewForm = new ViewForm(tableModel, this.listeners);
        this.frame.setContentPane(viewForm.panelMain);
        this.frame.setMinimumSize(viewForm.panelMain.getMinimumSize());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem item;
        item = new JMenuItem("Save As");
        FilenameFilter fileFilter = (file, s) -> s.toLowerCase().endsWith(".rctv");
        item.addActionListener(e -> {
            FileDialog fd = new FileDialog(this.frame, "Save file", FileDialog.SAVE);
            fd.setFilenameFilter(fileFilter);
            fd.setVisible(true);
            if (fd.getFile() != null) {
                String f = fd.getDirectory() + fd.getFile();
                File file = new File(f);
                try {
                    FileWriter out = new FileWriter(file);
                    this.listeners.forEach(listener -> listener.onSave(out));
                    out.flush();
                    out.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        menu.add(item);
        item = new JMenuItem("Open");
        item.addActionListener(e -> {
            FileDialog fd = new FileDialog(this.frame, "Open file", FileDialog.LOAD);
            fd.setFilenameFilter(fileFilter);
            fd.setVisible(true);
            if(fd.getFile() != null) {
                String f = fd.getDirectory() + fd.getFile();
                File file = new File(f);
                try {
                    Reader in = new FileReader(file);
                    this.listeners.forEach(listener -> listener.onOpen(in));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        menu.add(item);
        menuBar.add(menu);
        this.frame.setJMenuBar(menuBar);
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
