package front_end.AST.ExpressionAST;

import org.antlr.v4.runtime.ParserRuleContext;
import front_end.symbol_table.PAIR;

/**
 * Created by andikoh on 11/11/2016.
 */
public class PairliterAST extends ExpressionAST {
    String nullStr;
    public PairliterAST(ParserRuleContext ctx, String text) {
        super(ctx);
        nullStr = text;
        identObj = new PAIR(null, null);
    }

    @Override
    public void check() {
        checkIfInScope(nullStr);
    }

    @Override
    public void translate() {
    }
}
