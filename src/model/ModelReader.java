package model;

import diffName.DiffName;
import grammar.ModelListener;
import grammar.ReactiveLexer;
import grammar.ReactiveParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;

/**
 * Reads a model in from file/string
 */
public class ModelReader {
    private final Supplier<Model<DiffName>> factory;

    /**
     * Creates a model reader from the factory
     * @param factory produces an empty model
     */
    public ModelReader(Supplier<Model<DiffName>> factory) {
        this.factory = factory;
    }

    public Model<DiffName> read(Reader in) {
        CharStream cs;
        try {
            cs = CharStreams.fromReader(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("io error");
        }
        ReactiveLexer lexer = new ReactiveLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReactiveParser parser = new ReactiveParser(tokens);
        ReactiveParser.ProgramContext tree = parser.program();
        Model<DiffName> model = factory.get();
        ModelListener listener = new ModelListener(model);
        ParseTreeWalker.DEFAULT.walk(listener, tree);
        return model;
    }
}
