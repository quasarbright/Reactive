package view;

import model.DiffModel;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableView implements VisualView, ViewForm.ViewFormListener {
    private final JFrame frame;
    private final List<ViewListener> listeners;
    private Timer timer; // auto update period in milliseconds

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
        ViewForm viewForm = new ViewForm(tableModel, this.listeners, this);
        this.timer = new Timer(Integer.MAX_VALUE, viewForm.onUpdate);
        this.timer.stop();
        this.timer.setInitialDelay(0);
        this.timer.setDelay(1000);
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
            String filename = fd.getFile();
            if (filename != null) {
                if(!filename.toLowerCase().endsWith(".rctv")) {
                    filename += ".rctv";
                }
                String f = fd.getDirectory() + filename;
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
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
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
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
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

    @Override
    public void setRate(int rate) {
        this.timer.setDelay(rate);
    }

    @Override
    public void startAuto(int rate) {
        this.timer.setDelay(rate);
        this.timer.start();
    }

    @Override
    public void stopAuto() {
        this.timer.stop();
    }

    private void update() {
        this.frame.repaint();
        this.frame.revalidate();
    }

//    private void startAuto()
}
