import controller.Controller;
import controller.VisualController;
import model.CellGraph;
import model.DiffModel;
import view.SaveFileView;
import view.TableView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new VisualController(new DiffModel(new CellGraph<>()), TableView::new, SaveFileView::new);
        controller.open();
        /*
        TODO start and stop auto update based on a specified framerate
        TODO handle bad delta in update (dt with no t)
        TODO clean up relationship between view and form with more callbacks
        TODO undo and redo
        TODO graphing
        TODO save vs save as
        TODO display filename as window title, with a * if unsaved
         */
    }
}
