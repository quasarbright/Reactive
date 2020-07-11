package view;

import diffName.DiffName;
import expressions.Expr;
import model.Model;

public class SaveFileView implements TextView {
    private final Model<DiffName> model;

    public SaveFileView(Model<DiffName> model) {
        this.model = model;
    }

    @Override
    public String render() {
        StringBuilder ans = new StringBuilder();
        model.getNames().forEach(name -> {
            Expr<DiffName> expr = model.getExpr(name);
            ans.append(name.pretty()).append(" = ").append(expr.pretty()).append("\n");
        });
        return ans.toString();
    }
}
