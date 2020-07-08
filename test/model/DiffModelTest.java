package model;

import diffName.DeltaName;
import diffName.DiffName;
import diffName.VarName;
import expressions.TimesExpr;
import expressions.VarExpr;
import expressions.values.IntValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiffModelTest {
    private DiffModel g;
    private final DiffName x = new VarName("x");
    private final DiffName dx = new DeltaName(x);
    private final DiffName v = new VarName("v");
    private final DiffName dv = new DeltaName(v);
    private final DiffName a = new VarName("a");

    @Before
    public void setup() {
        g = new DiffModel(new CellGraph<>());
    }

    @Test
    public void updateSimple() {
        /*
        x = 0
        dx = 1
         */

        g.setCell(x, new IntValue<>(0));
        g.setCell(dx, new IntValue<>(1));
        assertEquals(new IntValue<>(0), g.getValue(x));
        assertEquals(new IntValue<>(1), g.getValue(dx));
        g.update();
        assertEquals(new IntValue<>(1), g.getValue(x));
        assertEquals(new IntValue<>(1), g.getValue(dx));
        g.update();
        assertEquals(new IntValue<>(2), g.getValue(x));
        assertEquals(new IntValue<>(1), g.getValue(dx));
        g.setCell(dx, new IntValue<>(10));
        g.update();
        assertEquals(new IntValue<>(12), g.getValue(x));
        assertEquals(new IntValue<>(10), g.getValue(dx));
        g.setCell(x, new IntValue<>(20));
        g.update();
        assertEquals(new IntValue<>(30), g.getValue(x));
    }

    @Test
    public void updateBadDelta() {
        g.setCell(dx, new IntValue<>(1));
        try {
            g.update();
            fail();
        } catch (IllegalStateException ignored) {

        }
    }

    @Test
    public void testSimultaneous() {
        g.setCell(x, new IntValue<>(1));
        g.setCell(dx, new VarExpr<>(v));
        g.setCell(v, new IntValue<>(1));
        g.setCell(dv, new VarExpr<>(x));
        g.update();
        assertEquals(new IntValue<>(2), g.getValue(x));
        assertEquals(new IntValue<>(2), g.getValue(v));
        g.update();
        assertEquals(new IntValue<>(4), g.getValue(x));
        assertEquals(new IntValue<>(4), g.getValue(v));
        g.update();
        assertEquals(new IntValue<>(8), g.getValue(x));
        assertEquals(new IntValue<>(8), g.getValue(v));
        g.update();
        assertEquals(new IntValue<>(16), g.getValue(x));
        assertEquals(new IntValue<>(16), g.getValue(v));
    }

    @Test
    public void testAccSimple() {
        g.setCell(x, new IntValue<>(0));
        g.setCell(dx, new VarExpr<>(v));
        g.setCell(v, new IntValue<>(0));
        g.setCell(dv, new VarExpr<>(a));
        g.setCell(a, new IntValue<>(1));
        g.update();
        assertEquals(new IntValue<>(0), g.getValue(x));
        assertEquals(new IntValue<>(1), g.getValue(v));
        assertEquals(new IntValue<>(1), g.getValue(a));
        g.update();
        assertEquals(new IntValue<>(1), g.getValue(x));
        assertEquals(new IntValue<>(2), g.getValue(v));
        assertEquals(new IntValue<>(1), g.getValue(a));
        g.update();
        assertEquals(new IntValue<>(3), g.getValue(x));
        assertEquals(new IntValue<>(3), g.getValue(v));
        assertEquals(new IntValue<>(1), g.getValue(a));
        g.update();
        assertEquals(new IntValue<>(6), g.getValue(x));
        assertEquals(new IntValue<>(4), g.getValue(v));
        assertEquals(new IntValue<>(1), g.getValue(a));
        g.update();
        assertEquals(new IntValue<>(10), g.getValue(x));
        assertEquals(new IntValue<>(5), g.getValue(v));
        assertEquals(new IntValue<>(1), g.getValue(a));
    }

    @Test
    public void complexDelta() {
        g.setCell(x, new IntValue<>(2));
        g.setCell(dx, new TimesExpr<>(new VarExpr<>(x), new VarExpr<>(dv)));
        g.setCell(v, new IntValue<>(3));
        g.setCell(dv, new TimesExpr<>(new VarExpr<>(x), new VarExpr<>(v)));
        g.update();
        assertEquals(new IntValue<>(14), g.getValue(x));
        assertEquals(new IntValue<>(9), g.getValue(v));
        g.update();
        assertEquals(new IntValue<>(1778), g.getValue(x));
        assertEquals(new IntValue<>(135), g.getValue(v));
    }
}