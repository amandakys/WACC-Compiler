import antlr.BasicParser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class FunctionParser extends BasicParser {
    public FunctionParser(TokenStream input) {
        super(input);
    }

    public void parse(ParseTree tree) {
        for(int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);
            if(child instanceof FunctionContext) {
                parse_function((FunctionContext) child);
            }
        }
    }

    private void parse_function(FunctionContext function) {
        check(function.statement());
    }

    private void check(StatementContext statement) {
        List<StatementContext> list = statement.statement();
        StatementContext last;

        if(list.size() > 0) {
            last = list.get(list.size() - 1);
        } else {
            last = statement;
        }

        if(last.RETURN() == null) {
            System.err.println("#syntax_error#");
            System.exit(100);
        }
    }
}
