package cells;

import expressions.DivideExpr;
import expressions.PlusExpr;
import expressions.TimesExpr;
import expressions.values.ErrorValue;
import expressions.values.IntValue;
import expressions.values.Value;
import expressions.VarExpr;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public abstract class ModelTest {

    protected Model g;

    /**
     * return an empty cell graph
     */
    protected abstract Model factory();

    @Before
    public void setup() {
        g = this.factory();
    }

    @Test
    public void getCell() {
        g.setCell("x", new IntValue(1));
        assertEquals(new Cell("x", new IntValue(1)).expr, g.getCell("x").expr);
        g.setCell("y", new IntValue(2));
        assertEquals(new Cell("y", new IntValue(2)).expr, g.getCell("y").expr);
        g.setCell("x", new IntValue(3));
        assertEquals(new Cell("x", new IntValue(3)).expr, g.getCell("x").expr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCellBadName() {
        g.getCell("x");
    }

    @Test
    public void getExpr() {
        g.setCell("x", new IntValue(1));
        assertEquals(new IntValue(1), g.getExpr("x"));
        g.setCell("y", new IntValue(2));
        assertEquals(new IntValue(2), g.getExpr("y"));
        g.setCell("x", new IntValue(3));
        assertEquals(new IntValue(3), g.getExpr("x"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getExprBadName() {
        g.getExpr("x");
    }

    @Test
    public void getValueSimple() {
        g.setCell("x", new IntValue(1));
        assertEquals(new IntValue(1), g.getValue("x"));
        g.setCell("y", new IntValue(2));
        assertEquals(new IntValue(2), g.getValue("y"));
        g.setCell("x", new IntValue(3));
        assertEquals(new IntValue(3), g.getValue("x"));
    }

    @Test
    public void getValueComplex() {
        // x = 2
        g.setCell("x", new IntValue(2));
        // y = x + 3
        g.setCell("y", new PlusExpr(new VarExpr("x"), new IntValue(3)));
        // z = x * y
        g.setCell("z", new TimesExpr(new VarExpr("x"), new VarExpr("y")));
        g.setCell("a", new PlusExpr(new PlusExpr(new IntValue(1), new IntValue(2)), new IntValue(3)));
        g.setCell("div0", new DivideExpr(new IntValue(1), new IntValue(0)));
        g.setCell("badVar", new VarExpr("functionsInJava"));
        g.setCell("refErr", new PlusExpr(new VarExpr("badVar"), new IntValue(1)));
        // request order shouldn't matter
        assertEquals(new IntValue(10), g.getValue("z"));
        assertEquals(new IntValue(5), g.getValue("y"));
        assertEquals(new IntValue(2), g.getValue("x"));
        assertEquals(new IntValue(6), g.getValue("a"));
        assertEquals(new ErrorValue("divide by zero"), g.getValue("div0"));
        assertEquals(new ErrorValue("name functionsInJava not found"), g.getValue("badVar"));
        assertEquals(new ErrorValue("name functionsInJava not found"), g.getValue("refErr"));
    }

    @Test
    public void circularReference() {
        g.setCell("x", new IntValue(2));
        try{
            g.setCell("x", new VarExpr("x"));
            fail();
        } catch (IllegalStateException e) {
            assertEquals(new IntValue(2), g.getValue("x"));
        }
    }

    @Test
    public void fib() {
        g.setCell("f_0", new IntValue(1));
        g.setCell("f_1", new IntValue(1));
        for(int i = 2; i <= 43; i++) {
            g.setCell(
                    "f_"+i,
                    new PlusExpr(
                            new VarExpr("f_"+(i-1)),
                            new VarExpr("f_"+(i-2))
                    )
            );
        }
        assertEquals(new IntValue(701408733), g.getValue("f_43"));
    }

    @Test
    public void getValueBadName() {
        assertEquals(new ErrorValue("name x not found"), g.getValue("x"));
    }

    @Test
    public void getValues() {
        // x = 2
        g.setCell("x", new IntValue(2));
        // y = x + 3
        g.setCell("y", new PlusExpr(new VarExpr("x"), new IntValue(3)));
        // z = x * y
        g.setCell("z", new TimesExpr(new VarExpr("x"), new VarExpr("y")));
        g.setCell("a", new PlusExpr(new PlusExpr(new IntValue(1), new IntValue(2)), new IntValue(3)));
        g.setCell("div0", new DivideExpr(new IntValue(1), new IntValue(0)));
        g.setCell("badVar", new VarExpr("functionsInJava"));
        g.setCell("refErr", new PlusExpr(new VarExpr("badVar"), new IntValue(1)));

        Map<String, Value> expected = new HashMap<>();
        expected.put("x", new IntValue(2));
        expected.put("y", new IntValue(5));
        expected.put("z", new IntValue(10));
        expected.put("a", new IntValue(6));
        expected.put("div0", new ErrorValue("divide by zero"));
        expected.put("badVar", new ErrorValue("name functionsInJava not found"));
        expected.put("refErr", new ErrorValue("name functionsInJava not found"));
        assertEquals(expected, g.getValues());
    }
}