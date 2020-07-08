package grammar;

import diffName.DiffName;
import expressions.Expr;
import model.Model;

public class ModelListener extends ReactiveBaseListener {

    private final Model<DiffName> model;

    public ModelListener(Model<DiffName> model) {
        this.model = model;
    }

    @Override
    public void enterAssignment(ReactiveParser.AssignmentContext ctx) {
        String lhs = ctx.IDENTIFIER().getText();
        DiffName name = ExprVisitor.getName(lhs);
        Expr<DiffName> rhs = ctx.expr().accept(new ExprVisitor());
        this.model.setCell(name, rhs);
    }
}
