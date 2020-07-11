package grammar;

import diffName.DeltaName;
import diffName.DiffName;
import diffName.VarName;
import expressions.*;
import expressions.values.DoubleValue;
import expressions.values.IntValue;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ExprVisitor extends ReactiveBaseVisitor<Expr<DiffName>> {
    @Override
    public Expr<DiffName> visitExpr(ReactiveParser.ExprContext ctx) {
        return this.visit(ctx.e);
    }

    @Override
    public Expr<DiffName> visitExprProgram(ReactiveParser.ExprProgramContext ctx) {
        return this.visit(ctx.expr());
    }

    @Override
    public Expr<DiffName> visitPlus(ReactiveParser.PlusContext ctx) {
        return new PlusExpr<>(this.visit(ctx.left), this.visit(ctx.right));
    }

    @Override
    public Expr<DiffName> visitMinus(ReactiveParser.MinusContext ctx) {
        return new MinusExpr<>(this.visit(ctx.left), this.visit(ctx.right));
    }

    @Override
    public Expr<DiffName> visitSDChild(ReactiveParser.SDChildContext ctx) {
        return this.visit(ctx.prodDiv());
    }

    @Override
    public Expr<DiffName> visitTimes(ReactiveParser.TimesContext ctx) {
        return new TimesExpr<>(this.visit(ctx.left), this.visit(ctx.right));
    }

    @Override
    public Expr<DiffName> visitDivide(ReactiveParser.DivideContext ctx) {
        return new DivideExpr<>(this.visit(ctx.left), this.visit(ctx.right));
    }

    @Override
    public Expr<DiffName> visitPDChild(ReactiveParser.PDChildContext ctx) {
        return this.visit(ctx.uminus());
    }

    @Override
    public Expr<DiffName> visitUMinus(ReactiveParser.UMinusContext ctx) {
        return new NegExpr<>(this.visit(ctx.child));
    }

    @Override
    public Expr<DiffName> visitUMChild(ReactiveParser.UMChildContext ctx) {
        return this.visit(ctx.parenExpr());
    }

    @Override
    public Expr<DiffName> visitParenExpr(ReactiveParser.ParenExprContext ctx) {
        if(ctx.expr() != null) {
            return this.visit(ctx.expr());
        } else {
            return this.visit(ctx.atomic());
        }
    }

    @Override
    public Expr<DiffName> visitDouble(ReactiveParser.DoubleContext ctx) {
        return new DoubleValue<>(Double.parseDouble(ctx.getText()));
    }

    @Override
    public Expr<DiffName> visitInteger(ReactiveParser.IntegerContext ctx) {
        return new IntValue<>(Integer.parseInt(ctx.getText()));
    }

    public static DiffName getName(String name) {
        if(name.length() >= 2 && name.charAt(0) == 'd') {
            name = name.substring(1);
            return new DeltaName(new VarName(name));
        } else {
            return new VarName(name);
        }
    }

    @Override
    public Expr<DiffName> visitIdentifier(ReactiveParser.IdentifierContext ctx) {
        String name = ctx.getText();
        return new VarExpr<>(getName(name));
    }

    public static Expr<DiffName> parseExr(String source) {
        ReactiveLexer lexer = new ReactiveLexer(CharStreams.fromString(source));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReactiveParser parser = new ReactiveParser(tokens);
        ReactiveParser.ExprProgramContext tree = parser.exprProgram();
        return tree.accept(new ExprVisitor());
    }
}
