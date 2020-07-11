package view;

import model.Model;

public interface VisualView {
    void show();
    void hide();
    void addListener(ViewListener listener);
}