package grammar;

import diffName.DeltaName;
import diffName.DiffName;
import diffName.VarName;
import expressions.*;
import expressions.values.DoubleValue;
import expressions.values.IntValue;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExprVisitorTest {

    private final DiffName x = new VarName("x");
    private final DiffName y = new VarName("y");
    private final DiffName dx = new DeltaName(x);
    private final DiffName dy = new DeltaName(y);
    private final DiffName dan = new DeltaName(new VarName("an"));
    private final DiffName temp = new VarName("temp");
    private final DiffName dtemp = new DeltaName(temp);

    private void tParse(String source, Expr<DiffName> expected) {
        assertEquals(expected, ExprVisitor.parseExr(source));
    }

    @Test
    public void double_() {
        tParse("1.0", new DoubleValue<>(1.0));
        tParse("1.00", new DoubleValue<>(1.0));
        tParse("1.", new DoubleValue<>(1.0));
        tParse(".1", new DoubleValue<>(.1));
        tParse("-1.0", new DoubleValue<>(-1.0));
        tParse("-1.", new DoubleValue<>(-1.0));
        tParse("-.1", new DoubleValue<>(-.1));
    }

    @Test
    public void int_() {
        tParse("1", new IntValue<>(1));
        tParse("11", new IntValue<>(11));
        tParse("-1", new IntValue<>(-1));
        tParse("-11", new IntValue<>(-11));
    }

    @Test
    public void var() {
        tParse("x", new VarExpr<>(x));
        tParse("y", new VarExpr<>(y));
        tParse("temp", new VarExpr<>(temp));
        tParse("var0", new VarExpr<>(new VarName("var0")));
        tParse("dx", new VarExpr<>(dx));
        tParse("dtemp", new VarExpr<>(dtemp));
        tParse("dan", new VarExpr<>(dan));
    }

    @Test
    public void plus() {
        tParse("1 + 2", new PlusExpr<>(new IntValue<>(1), new IntValue<>(2)));
        tParse("1 + -2", new PlusExpr<>(new IntValue<>(1), new IntValue<>(-2)));
        tParse("1 + (2 + 3)", new PlusExpr<>(new IntValue<>(1), new PlusExpr<>(new IntValue<>(2), new IntValue<>(3))));
        tParse("(1 + 2) + 3", new PlusExpr<>(new PlusExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
        tParse("1 + 2 + 3", new PlusExpr<>(new PlusExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
    }

    @Test
    public void minus() {
        tParse("1 - 2", new MinusExpr<>(new IntValue<>(1), new IntValue<>(2)));
        tParse("1 - -2", new MinusExpr<>(new IntValue<>(1), new IntValue<>(-2)));
        tParse("1 - (2 - 3)", new MinusExpr<>(new IntValue<>(1), new MinusExpr<>(new IntValue<>(2), new IntValue<>(3))));
        tParse("(1 - 2) - 3", new MinusExpr<>(new MinusExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
        tParse("1 - 2 - 3", new MinusExpr<>(new MinusExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
    }

    @Test
    public void times() {
        tParse("1 * 2", new TimesExpr<>(new IntValue<>(1), new IntValue<>(2)));
        tParse("1 * -2", new TimesExpr<>(new IntValue<>(1), new IntValue<>(-2)));
        tParse("1 * (2 * 3)", new TimesExpr<>(new IntValue<>(1), new TimesExpr<>(new IntValue<>(2), new IntValue<>(3))));
        tParse("(1 * 2) * 3", new TimesExpr<>(new TimesExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
        tParse("1 * 2 * 3", new TimesExpr<>(new TimesExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
    }

    @Test
    public void divide() {
        tParse("1 / 2", new DivideExpr<>(new IntValue<>(1), new IntValue<>(2)));
        tParse("1 / -2", new DivideExpr<>(new IntValue<>(1), new IntValue<>(-2)));
        tParse("1 / (2 / 3)", new DivideExpr<>(new IntValue<>(1), new DivideExpr<>(new IntValue<>(2), new IntValue<>(3))));
        tParse("(1 / 2) / 3", new DivideExpr<>(new DivideExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
        tParse("1 / 2 / 3", new DivideExpr<>(new DivideExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
    }

    @Test
    public void pemdas() {
        tParse("1 + 2 * 3", new PlusExpr<>(new IntValue<>(1), new TimesExpr<>(new IntValue<>(2), new IntValue<>(3))));
        tParse("(1 + 2) * 3", new TimesExpr<>(new PlusExpr<>(new IntValue<>(1), new IntValue<>(2)), new IntValue<>(3)));
        tParse("1 * 2 / 3 + 4 - 5 * 6",
                new MinusExpr<>(
                    new PlusExpr<>(
                        new DivideExpr<>(
                                new TimesExpr<>(new IntValue<>(1), new IntValue<>(2)),
                                new IntValue<>(3)),
                    new IntValue<>(4)),
                new TimesExpr<>(new IntValue<>(5), new IntValue<>(6))));
    }

    @Test
    public void unaryMinus() {
        tParse("2-1", new MinusExpr<>(new IntValue<>(2), new IntValue<>(1)));
    }
}