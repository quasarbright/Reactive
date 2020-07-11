package controller;

import diffName.DiffName;
import expressions.Expr;
import model.DiffModel;
import model.ModelReader;
import view.VisualView;

import java.io.StringReader;
import java.util.Optional;
import java.util.function.Function;

public class VisualController implements Controller {
    private final DiffModel model;
    private final Function<DiffModel, VisualView> viewFactory;

    public VisualController(DiffModel model, Function<DiffModel, VisualView> viewFactory) {
        this.model = model;
        this.viewFactory = viewFactory;
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
}
