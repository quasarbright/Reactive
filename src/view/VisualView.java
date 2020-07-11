package view;

public interface VisualView {
    void show();
    void hide();
    void addListener(ViewListener listener);
}