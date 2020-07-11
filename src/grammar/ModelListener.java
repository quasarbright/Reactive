package grammar;

import diffName.DiffName;
import expressions.Expr;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class ModelListener extends ReactiveBaseListener {

    public final List<Pair<DiffName,Expr<DiffName>>> assignments;

    public ModelListener() {
        this.assignments = new ArrayList<>();
    }

    @Override
    public void enterAssignment(ReactiveParser.AssignmentContext ctx) {
        String lhs = ctx.IDENTIFIER().getText();
        DiffName name = ExprVisitor.getName(lhs);
        Expr<DiffName> rhs = ctx.expr().accept(new ExprVisitor());
        this.assignments.add(new Pair<>(name, rhs));
    }
}
