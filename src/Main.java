import controller.Controller;
import controller.VisualController;
import diffName.DiffName;
import grammar.ExprVisitor;
import model.CellGraph;
import model.DiffModel;
import model.Model;
import model.ModelReader;
import org.antlr.v4.runtime.RecognitionException;
import view.TableView;
import view.VisualView;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
//        ModelReader reader = new ModelReader(() -> new DiffModel(new CellGraph<>()));
//        DiffModel model = (DiffModel) reader.read(new StringReader("x = 1+y\ny = 0\ndx = x\na=1/0"));
//        DiffModel model = (DiffModel) reader.read(new StringReader("x"));
//        VisualView view = new TableView(model);
//        view.show();
        Controller controller = new VisualController(new DiffModel(new CellGraph<>()), TableView::new);
        controller.open();
    }
}
