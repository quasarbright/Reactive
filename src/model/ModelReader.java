package model;

import diffName.DiffName;
import grammar.ModelListener;
import grammar.ReactiveLexer;
import grammar.ReactiveParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Reads a model in from file/string
 */
public class ModelReader {
    private final Supplier<Model<DiffName>> factory;
    private final Consumer<RuntimeException> onBadEdit;
    /**
     * Creates a model reader from the factory
     * @param factory produces an empty model
     */
    public ModelReader(Supplier<Model<DiffName>> factory) {
        this(factory, (e) -> {
            if (!e.getLocalizedMessage().contains("cyclic definition")) {
                throw e;
            }
        });
    }
    public ModelReader(Supplier<Model<DiffName>> factory, Consumer<RuntimeException> onBadEdit) {
        this.factory = factory;
        this.onBadEdit = onBadEdit;
    }

    public Model<DiffName> read(Reader in) {
        CharStream cs;
        try {
            cs = CharStreams.fromReader(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("io error");
        }
        boolean[] parseError = new boolean[]{false};
        BaseErrorListener errorListener = new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                onBadEdit.accept(new IllegalStateException(s));
                parseError[0] = true;
            }
        };
        ReactiveLexer lexer = new ReactiveLexer(cs);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ReactiveParser parser = new ReactiveParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ReactiveParser.ProgramContext tree = parser.program();
        Model<DiffName> model = factory.get();
        if(!parseError[0]) {
            // trying to parse on a parse error leads to null pointer exceptions
            ModelListener listener = new ModelListener();
            ParseTreeWalker.DEFAULT.walk(listener, tree);
            listener.assignments.forEach(p -> {
                try {
                    model.setCell(p.a, p.b);
                } catch (RuntimeException e) {
                    this.onBadEdit.accept(e);
                }
            });
        }
        return model;
    }
}
