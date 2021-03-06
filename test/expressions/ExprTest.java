package expressions;

import expressions.values.DoubleValue;
import expressions.values.ErrorValue;
import expressions.values.IntValue;
import expressions.values.Value;
import org.junit.Test;

import java.util.Set;
import java.util.function.Function;

import static org.junit.Assert.*;

public class ExprTest {
    final Function<String, Value<String>> env = (name) -> {
      switch (name) {
          case "x":
              return new IntValue<>(10);
          case "y":
              return new DoubleValue<>(20.0);
          default:
              return new ErrorValue<>("name "+name+" is not defined");
      }
    };
    final Value<String> err = new ErrorValue<>("error");
    final Value<String> div0 = new ErrorValue<>("divide by zero");

    public void tExpr(Expr<String> expr, Value<String> expected) {
        assertEquals(expected, expr.evaluate(env));
    }

    @Test
    public void testPlusExpr() {
        Expr<String> expr;
        expr = new PlusExpr<>(new IntValue<>(1), new IntValue<>(2));
        assertEquals(new IntValue<>(3), expr.evaluate(env));
        expr = new PlusExpr<>(new DoubleValue<>(1.0), new DoubleValue<>(2.5));
        assertEquals(new DoubleValue<>(3.5), expr.evaluate(env));
        expr = new PlusExpr<>(new DoubleValue<>(1.0), new IntValue<>(2));
        assertEquals(new DoubleValue<>(3.0), expr.evaluate(env));
        expr = new PlusExpr<>(new IntValue<>(2), new DoubleValue<>(1.0));
        assertEquals(new DoubleValue<>(3.0), expr.evaluate(env));
        expr = new PlusExpr<>(new IntValue<>(1), err);
        assertEquals(err, expr.evaluate(env));
        expr = new PlusExpr<>(err, new IntValue<>(1));
        assertEquals(err, expr.evaluate(env));
        expr = new PlusExpr<>(err, err);
        assertEquals(err, expr.evaluate(env));
    }

    @Test
    public void testMinusExpr() {
        Expr<String> expr;
        expr = new MinusExpr<>(new IntValue<>(1), new IntValue<>(2));
        assertEquals(new IntValue<>(-1), expr.evaluate(env));
        expr = new MinusExpr<>(new DoubleValue<>(1.0), new DoubleValue<>(2.5));
        assertEquals(new DoubleValue<>(-1.5), expr.evaluate(env));
        expr = new MinusExpr<>(new DoubleValue<>(1.0), new IntValue<>(2));
        assertEquals(new DoubleValue<>(-1.0), expr.evaluate(env));
        expr = new MinusExpr<>(new IntValue<>(2), new DoubleValue<>(1.0));
        assertEquals(new DoubleValue<>(1.0), expr.evaluate(env));
        expr = new MinusExpr<>(new IntValue<>(1), err);
        assertEquals(err, expr.evaluate(env));
        expr = new MinusExpr<>(err, new IntValue<>(1));
        assertEquals(err, expr.evaluate(env));
        expr = new MinusExpr<>(err, err);
        assertEquals(err, expr.evaluate(env));
    }

    @Test
    public void testTimesExpr() {
        Expr<String> expr;
        expr = new TimesExpr<>(new IntValue<>(1), new IntValue<>(2));
        assertEquals(new IntValue<>(2), expr.evaluate(env));
        expr = new TimesExpr<>(new DoubleValue<>(1.0), new DoubleValue<>(2.5));
        assertEquals(new DoubleValue<>(2.5), expr.evaluate(env));
        expr = new TimesExpr<>(new DoubleValue<>(1.0), new IntValue<>(2));
        assertEquals(new DoubleValue<>(2), expr.evaluate(env));
        expr = new TimesExpr<>(new IntValue<>(2), new DoubleValue<>(1.0));
        assertEquals(new DoubleValue<>(2), expr.evaluate(env));
        expr = new TimesExpr<>(new IntValue<>(1), err);
        assertEquals(err, expr.evaluate(env));
        expr = new TimesExpr<>(err, new IntValue<>(1));
        assertEquals(err, expr.evaluate(env));
        expr = new TimesExpr<>(err, err);
        assertEquals(err, expr.evaluate(env));
    }

    @Test
    public void testDivideExpr() {
        Expr<String> expr;
        expr = new DivideExpr<>(new IntValue<>(1), new IntValue<>(2));
        assertEquals(new IntValue<>(0), expr.evaluate(env));
        expr = new DivideExpr<>(new IntValue<>(10), new IntValue<>(5));
        assertEquals(new IntValue<>(2), expr.evaluate(env));
        expr = new DivideExpr<>(new IntValue<>(8), new IntValue<>(3));
        assertEquals(new IntValue<>(2), expr.evaluate(env));
        expr = new DivideExpr<>(new DoubleValue<>(1.0), new DoubleValue<>(2.0));
        assertEquals(new DoubleValue<>(0.5), expr.evaluate(env));
        expr = new DivideExpr<>(new DoubleValue<>(1.0), new IntValue<>(2));
        assertEquals(new DoubleValue<>(0.5), expr.evaluate(env));
        expr = new DivideExpr<>(new IntValue<>(2), new DoubleValue<>(1.0));
        assertEquals(new DoubleValue<>(2.0), expr.evaluate(env));
        expr = new DivideExpr<>(new IntValue<>(1), err);
        assertEquals(err, expr.evaluate(env));
        expr = new DivideExpr<>(err, new IntValue<>(1));
        assertEquals(err, expr.evaluate(env));
        expr = new DivideExpr<>(err, err);
        assertEquals(err, expr.evaluate(env));

        expr = new DivideExpr<>(new IntValue<>(1), new IntValue<>(0));
        assertEquals(div0, expr.evaluate(env));
        expr = new DivideExpr<>(new DoubleValue<>(1), new DoubleValue<>(0));
        assertEquals(div0, expr.evaluate(env));
        expr = new DivideExpr<>(new IntValue<>(1), new DoubleValue<>(0));
        assertEquals(div0, expr.evaluate(env));
        expr = new DivideExpr<>(new DoubleValue<>(1), new IntValue<>(0));
        assertEquals(div0, expr.evaluate(env));
        expr = new DivideExpr<>(new IntValue<>(0), new IntValue<>(100));
        assertEquals(new IntValue<>(0), expr.evaluate(env));
        expr = new DivideExpr<>(new DoubleValue<>(0), new DoubleValue<>(100));
        assertEquals(new DoubleValue<>(0), expr.evaluate(env));
    }

    @Test
    public void testVarExpr() {
        Expr<String> expr;
        expr = new VarExpr<>("x");
        assertEquals(new IntValue<>(10), expr.evaluate(env));
        tExpr(new VarExpr<>("y"), new DoubleValue<>(20.0));
        tExpr(new VarExpr<>("z"), new ErrorValue<>("name z is not defined"));
    }

    @Test
    public void testNegateExpr() {
        tExpr(new NegExpr<>(new PlusExpr<>(new IntValue<>(1), new IntValue<>(2))), new IntValue<>(-3));
    }

    @Test
    public void testComplex() {
        // 2*((x + 0) / (6 - 3)) * y
        tExpr(
                new TimesExpr<>(
                        new TimesExpr<>(
                                new IntValue<>(2),
                                new DivideExpr<>(
                                        new PlusExpr<>(new VarExpr<>("x"), new IntValue<>(0)),
                                        new MinusExpr<>(new IntValue<>(6), new IntValue<>(3))
                                )
                        ),
                        new VarExpr<>("y")
                ),
                new DoubleValue<>(120.0));

        tExpr(
                new TimesExpr<>(new IntValue<>(1), new DivideExpr<>(new IntValue<>(1), new IntValue<>(0))),
                div0
        );
    }

    private void tFreeVars(Expr<String> expr, String... vars) {
        assertEquals(Set.of(vars), expr.getFreeVars());
    }

    @Test
    public void freeVars() {
        tFreeVars(new VarExpr<>("x"), "x");
        tFreeVars(new IntValue<>(1));
        tFreeVars(new PlusExpr<>(new VarExpr<>("x"), new PlusExpr<>(new PlusExpr<>(new VarExpr<>("x"), new IntValue<>(1)), new VarExpr<>("y"))), "x", "y");
    }
}