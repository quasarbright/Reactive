package controller;

import model.CellGraph;
import model.DiffModel;
import model.ModelReader;
import view.TextView;
import view.VisualView;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;
import java.util.function.Function;

public class VisualController implements Controller {
    private DiffModel model;
    private final Function<DiffModel, VisualView> viewFactory;
    private final Function<DiffModel, TextView> saveViewFactory;

    public VisualController(DiffModel model, Function<DiffModel, VisualView> viewFactory, Function<DiffModel, TextView> saveViewFactory) {
        this.model = model;
        this.viewFactory = viewFactory;
        this.saveViewFactory = saveViewFactory;
    }

    @Override
    public void open() {
        VisualView view = viewFactory.apply(this.model);
        view.addListener(this);
        view.show();
    }

    @Override
    public Optional<String> onEdit(String assignment) {
        final String[] ans = new String[]{null};
        ModelReader reader = new ModelReader(() -> this.model, (e) -> ans[0] = e.getLocalizedMessage());
        // TODO handle parse errors
        reader.read(new StringReader(assignment));
        return Optional.ofNullable(ans[0]);
    }

    @Override
    public void onUpdate() {
        model.update();
    }

    @Override
    public void onSave(Appendable out) {
        TextView view = this.saveViewFactory.apply(this.model);
        try {
            out.append(view.render());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(Reader in) {
        ModelReader reader = new ModelReader(() -> new DiffModel(new CellGraph<>()));
        // TODO handle reading errors even though it should be impossible to save a file with those kinds of errors
        DiffModel model = (DiffModel) reader.read(in);
        new VisualController(model, this.viewFactory, this.saveViewFactory).open();
    }
}
