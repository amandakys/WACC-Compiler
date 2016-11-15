package AST.ExpressionAST;

import AST.ExpressionAST.ExpressionAST;
import main.Visitor;
import org.antlr.v4.runtime.ParserRuleContext;
import symbol_table.IDENTIFIER;
import symbol_table.PAIR;

/**
 * Created by andikoh on 11/11/2016.
 */
public class PairliterAST extends ExpressionAST {
    String nullStr;

    public PairliterAST(ParserRuleContext ctx, String text) {
        super(ctx);
        nullStr = text;
    }

    @Override
    public void check() {
        identObj = new PAIR(null, null);
    }
}
