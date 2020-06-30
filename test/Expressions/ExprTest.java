package Expressions;

import Expressions.Values.DoubleValue;
import Expressions.Values.ErrorValue;
import Expressions.Values.IntValue;
import Expressions.Values.Value;

import java.util.function.Function;

import static org.junit.Assert.*;

public class ExprTest {
    Function<String, Value> env = (name) -> {
      switch (name) {
          case "x":
              return new IntValue(10);
          case "y":
              return new DoubleValue(20);
          default:
              return new ErrorValue("name "+name+" is not defined");
      }
    };
    Value err = new ErrorValue("error");

    @org.junit.Test
    public void testPlusExpr() {
        Expr expr;
        expr = new PlusExpr(new IntValue(1), new IntValue(2));
        assertEquals(new IntValue(3), expr.evaluate(env));
        expr = new PlusExpr(new DoubleValue(1.0), new DoubleValue(2.5));
        assertEquals(new DoubleValue(3.5), expr.evaluate(env));
        expr = new PlusExpr(new DoubleValue(1.0), new IntValue(2));
        assertEquals(new DoubleValue(3.0), expr.evaluate(env));
        expr = new PlusExpr(new IntValue(2), new DoubleValue(1.0));
        assertEquals(new DoubleValue(3.0), expr.evaluate(env));
        expr = new PlusExpr(new IntValue(1), err);
        assertEquals(err, expr.evaluate(env));
        expr = new PlusExpr(err, new IntValue(1));
        assertEquals(err, expr.evaluate(env));
        expr = new PlusExpr(err, err);
        assertEquals(err, expr.evaluate(env));
    }
}