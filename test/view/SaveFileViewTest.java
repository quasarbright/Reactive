package view;

import diffName.DiffName;
import diffName.VarName;
import expressions.values.IntValue;
import model.CellGraph;
import model.DiffModel;
import model.Model;
import model.ModelReader;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class SaveFileViewTest {
    private Model<DiffName> testRoundTrip(String source) {
        ModelReader reader = new ModelReader(() -> new DiffModel(new CellGraph<>()));
        Model<DiffName> model = reader.read(new StringReader(source));

        source = new SaveFileView(model).render();
        Model<DiffName> newModel = reader.read(new StringReader(source));
        assertEquals(model.getValues(), newModel.getValues());

        // again
        source = new SaveFileView(newModel).render();
        newModel = reader.read(new StringReader(source));
        assertEquals(model.getValues(), newModel.getValues());
        return model;
    }

    @Test
    public void roundTrip() {
        testRoundTrip("");
        testRoundTrip("x = 1");
        testRoundTrip("dx = 1");
        testRoundTrip("x = 1+y\ny = 0\ndx=x");
    }

    @Test
    public void roundTripWithErrors() {
        testRoundTrip("x = a\ny = 1/0");
    }

    @Test
    public void roundTripWithCycle() {
        Model<DiffName> model = testRoundTrip("x = x\ny = 1");
        assertEquals(model.getValue(new VarName("y")), new IntValue<>(1));
    }
}