package Expressions;

public class VarExpr implements Expr {
    private String name;

    public VarExpr(String name) {
        this.name = name;
    }
}
