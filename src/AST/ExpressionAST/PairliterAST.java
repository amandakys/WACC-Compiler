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
//        IDENTIFIER pair = Visitor.ST.lookUpAll("pair");
//        identObj = pair;
    }

    @Override
    public void check() {
        checkIfInScope(nullStr);
        identObj = new PAIR(null, null);
    }
}
